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
		
		String query = "SELECT * FROM myfile ORDER BY idx DESC";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}
