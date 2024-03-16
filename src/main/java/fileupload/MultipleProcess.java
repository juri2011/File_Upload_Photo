package fileupload;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MultipleProcess.do")
// 각 파일 최대 크기 1MB, 전체 파일 최대 크기 10MB
// annotation을 사용하지 않는다면 web.xml로 설정할 수 있다.
@MultipartConfig(
	maxFileSize = 1024 * 1024 * 1,
	maxRequestSize = 1024 * 1024 * 10
)
public class MultipleProcess extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("ok");
		
		//서버의 Uploads 디렉토리의 절대 경로를 받아온다.
		//이미 Uploads 디렉토리를 만들었는데 물리적 경로가 따로 필요한 이유?
		// -> OS에 따라 경로를 표현하는 방법이 다르기 때문
		
		//예외 처리(파일 용량 제한 초과 등 에러가 발생했을 때 FileUploadMain.jsp에 오류메시지를 전달하기 위함)
		try {
			String saveDirectory = getServletContext().getRealPath("/Uploads");
			//파일을 업로드하고 이름을 받아온다.
			ArrayList<String> listFileName = FileUtil.multipleFile(req, saveDirectory);
			for(String originalFileName : listFileName) {
				//파일 이름 변경
				String savedFileName = FileUtil.renameFile(saveDirectory, originalFileName);
				
				insertMyFile(req, originalFileName, savedFileName);
			}
			
			//FileList.jsp로 이동
			resp.sendRedirect("FileList.jsp");
			
		}catch (Exception e){
			e.printStackTrace();
			req.setAttribute("errorMessage", "파일 업로드 오류");
			req.getRequestDispatcher("FileUploadMain.jsp").forward(req, resp);
		}
		
	}
	
	private void insertMyFile(HttpServletRequest req, String oFileName, String sFileName) {
		//form으로부터 제목 받아오기
		String title = req.getParameter("title");
		//form으로부터 카테고리 배열로 받아오기
		String[] cateArray = req.getParameterValues("cate");
		StringBuffer cateBuf = new StringBuffer();
		//아무 카테고리도 선택하지 않았으면...
		if(cateArray == null) {
			cateBuf.append("선택한 항목 없음");
		}else {
			for(String s: cateArray) {
				cateBuf.append(s + ", ");
			}
		}
		
		MyFileDTO dto = new MyFileDTO();
		dto.setTitle(title);
		dto.setCate(cateBuf.toString());
		dto.setOfile(oFileName);
		dto.setSfile(sFileName);
		
		MyFileDAO dao = new MyFileDAO();
		dao.insertFile(dto);
		dao.close();
	}
	

}
