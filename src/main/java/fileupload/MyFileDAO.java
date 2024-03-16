package fileupload;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import common.DBConnPool;

public class MyFileDAO extends DBConnPool{
	
	public int insertFile(MyFileDTO dto) {
		int applyResult = 0;
		
		try {
			String query = "INSERT INTO myfile("
					+ "idx, title, cate, ofile, sfile) "
					+ "VALUES ("
					+ "seq_board_num.nextval, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getCate());
			pstmt.setString(3, dto.getOfile());
			pstmt.setString(4, dto.getSfile());
			
			applyResult = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("INSERT 중 예외 발생");
			e.printStackTrace();
		}
		
		return applyResult;
	}
	
	public List<MyFileDTO> myFileList(){
		List<MyFileDTO> fileList = new Vector<MyFileDTO>();
		
		//내림차순으로 정렬하여 최신 게시물 먼저 출력
		String query = "SELECT * FROM myfile ORDER BY idx DESC";
		try {
			//정적 쿼리문을 실행할 때는 Statement객체를 사용한다.
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				MyFileDTO dto = new MyFileDTO();
				dto.setIdx(rs.getString(1));
				dto.setTitle(rs.getString(2));
				dto.setCate(rs.getString(3));
				dto.setOfile(rs.getString(4));
				dto.setSfile(rs.getString(5));
				dto.setPostdate(rs.getString(6));
				
				fileList.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return fileList;
	}
	

}
