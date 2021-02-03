package forwarders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class LclQuotationMgr {

	private DBConnectionMgr pool;
	
	public LclQuotationMgr() {
		pool = DBConnectionMgr.getInstance();
	}
	
	public boolean insertLclQuotation(LclQuotationBean lclq) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "insert lclQuotation(no,pickupRate,ofRate,customsBrokerRate,blDocRate,consolThcRate,portTransferRate,vgmRate,amsHandlingRate,usd,won,        remarks,oftype,carrier,tt,validity,date,state,id) "
					+ "values(?,?,?,?,?,?,?,?,?,?,?     ,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, lclq.getNo());
			pstmt.setInt(2, lclq.getPickupRate());
			pstmt.setInt(3, lclq.getOfRate());
			pstmt.setInt(4, lclq.getCustomsBrokerRate());
			pstmt.setInt(5, lclq.getBlDocRate());
			pstmt.setInt(6, lclq.getConsolThcRate());
			pstmt.setInt(7, lclq.getPortTransferRate());
			pstmt.setInt(8, lclq.getVgmRate());
			pstmt.setInt(9, lclq.getAmsHandlingRate());
			pstmt.setInt(10, lclq.getUsd());
			pstmt.setInt(11, lclq.getWon());
			
			pstmt.setString(12, lclq.getRemarks());
			pstmt.setString(13, lclq.getOftype());
			pstmt.setString(14, lclq.getCarrier());
			pstmt.setString(15, lclq.getTt());
			pstmt.setString(16, lclq.getValidity());
			pstmt.setString(17, UtilMgr.getDay());
			pstmt.setString(18, "1");
			pstmt.setString(19, lclq.getId());
			
			if(pstmt.executeUpdate()==1) flag = true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
	
	//Detail
	public LclQuotationBean getLclQuotationDetail(int no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		LclQuotationBean quotation = new LclQuotationBean();
		try {
			con = pool.getConnection();
			sql = "select * from lclQuotation where no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				quotation.setLclno(rs.getInt("lclno"));
				quotation.setNo(rs.getInt("no"));
				quotation.setPickupRate(rs.getInt("pickupRate"));
				quotation.setOfRate(rs.getInt("ofRate"));
				quotation.setCustomsBrokerRate(rs.getInt("customsBrokerRate"));
				quotation.setBlDocRate(rs.getInt("blDocRate"));
				quotation.setConsolThcRate(rs.getInt("consolThcRate"));
				quotation.setPortTransferRate(rs.getInt("portTransferRate"));
				quotation.setVgmRate(rs.getInt("vgmRate"));
				quotation.setAmsHandlingRate(rs.getInt("amsHandlingRate"));
				quotation.setUsd(rs.getInt("usd"));
				quotation.setWon(rs.getInt("won"));
				
				
				quotation.setRemarks(rs.getString("remarks"));
				quotation.setOftype(rs.getString("oftype"));
				quotation.setCarrier(rs.getString("carrier"));
				quotation.setTt(rs.getString("tt"));
				quotation.setValidity(rs.getString("validity"));
				quotation.setDate(rs.getString("date"));
				quotation.setState(rs.getString("state"));
				quotation.setId(rs.getString("id"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return quotation;
	}
	
	//list(사용자용)
	public Vector<LclQuotationBean> getLclQuotationList(String id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<LclQuotationBean> vlist= new Vector<LclQuotationBean>();
		try {
			con = pool.getConnection();
			sql = "select * from lclQuotation where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				LclQuotationBean quotation = new LclQuotationBean();
				quotation.setLclno(rs.getInt("lclno"));
				quotation.setNo(rs.getInt("no"));
				quotation.setPickupRate(rs.getInt("pickupRate"));
				quotation.setOfRate(rs.getInt("ofRate"));
				quotation.setCustomsBrokerRate(rs.getInt("customsBrokerRate"));
				quotation.setBlDocRate(rs.getInt("blDocRate"));
				quotation.setConsolThcRate(rs.getInt("consolThcRate"));
				quotation.setPortTransferRate(rs.getInt("portTransferRate"));
				quotation.setVgmRate(rs.getInt("vgmRate"));
				quotation.setAmsHandlingRate(rs.getInt("amsHandlingRate"));
				quotation.setUsd(rs.getInt("usd"));
				quotation.setWon(rs.getInt("won"));
				
				
				quotation.setRemarks(rs.getString("remarks"));
				quotation.setOftype(rs.getString("oftype"));
				quotation.setCarrier(rs.getString("carrier"));
				quotation.setTt(rs.getString("tt"));
				quotation.setValidity(rs.getString("validity"));
				quotation.setDate(rs.getString("date"));
				quotation.setState(rs.getString("state"));
				quotation.setId(rs.getString("id"));	
				vlist.addElement(quotation);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}
	
	//Order All List(포워딩 회사 용)
	public Vector<LclQuotationBean> getLclQuotationList(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<LclQuotationBean> vlist= new Vector<LclQuotationBean>();
		try {
			con = pool.getConnection();
			sql = "select * from lclQuotation order by lclno desc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				LclQuotationBean quotation = new LclQuotationBean();
				quotation.setLclno(rs.getInt("lclno"));
				quotation.setNo(rs.getInt("no"));
				quotation.setPickupRate(rs.getInt("pickupRate"));
				quotation.setOfRate(rs.getInt("ofRate"));
				quotation.setCustomsBrokerRate(rs.getInt("customsBrokerRate"));
				quotation.setBlDocRate(rs.getInt("blDocRate"));
				quotation.setConsolThcRate(rs.getInt("consolThcRate"));
				quotation.setPortTransferRate(rs.getInt("portTransferRate"));
				quotation.setVgmRate(rs.getInt("vgmRate"));
				quotation.setAmsHandlingRate(rs.getInt("amsHandlingRate"));
				quotation.setUsd(rs.getInt("usd"));
				quotation.setWon(rs.getInt("won"));
				
				
				quotation.setRemarks(rs.getString("remarks"));
				quotation.setOftype(rs.getString("oftype"));
				quotation.setCarrier(rs.getString("carrier"));
				quotation.setTt(rs.getString("tt"));
				quotation.setValidity(rs.getString("validity"));
				quotation.setDate(rs.getString("date"));
				quotation.setState(rs.getString("state"));
				quotation.setId(rs.getString("id"));	
				vlist.addElement(quotation);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}
		
}
