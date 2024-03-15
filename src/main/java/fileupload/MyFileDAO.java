package fileupload;

import common.DBConnPool;

public class MyFileDAO extends DBConnPool{
	public int insertFile(MyFileDTO dto) {
		int applyResult = 0;
		String query = "INSERT INTO myfile("
				+ "idx, title, cate, ofile, sfile) "
				+ "VALUES ("
				+ "seq_board_num.nextval, ?, ?, ?, ?, ?)";
		
		
		return applyResult;
	}
}
