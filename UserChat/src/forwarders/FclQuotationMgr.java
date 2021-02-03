package forwarders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import fmember.ForwardersMemberBean;

public class FclQuotationMgr {

	private DBConnectionMgr pool;
	
	public FclQuotationMgr() {
		pool=DBConnectionMgr.getInstance();
	}
	

	public boolean insertFclQuotation(FclQuotationBean fclq) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "insert fclQuotation(no,pickupRate,stuffingRate,lashingRate,ofRate,lssebs,customsBrokerRate,thcRate,otherRate,amsRate,vgmRate,handlingRate,won,usd,       remarks,oftype,carrier,tt,validity,date,state,id) "
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?     ,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, fclq.getNo());//해봐 손땔게내가함?
			pstmt.setInt(2, fclq.getPickupRate());
			pstmt.setInt(3, fclq.getStuffingRate());
			pstmt.setInt(4, fclq.getLashingRate());
			pstmt.setInt(5, fclq.getOfRate());
			pstmt.setInt(6, fclq.getLssebs());
			pstmt.setInt(7, fclq.getCustomsBrokerRate());
			pstmt.setInt(8, fclq.getThcRate());
			pstmt.setInt(9, fclq.getOtherRate());
			pstmt.setInt(10, fclq.getAmsRate());
			pstmt.setInt(11, fclq.getVgmRate());
			pstmt.setInt(12, fclq.getHandlingRate());
			pstmt.setInt(13, fclq.getWon());
			pstmt.setInt(14, fclq.getUsd());
			
			
			pstmt.setString(15, fclq.getRemarks());
			pstmt.setString(16, fclq.getOftype());
			pstmt.setString(17,  fclq.getCarrier());
			pstmt.setString(18, fclq.getTt());
			pstmt.setString(19, fclq.getValidity());
			pstmt.setString(20, UtilMgr.getDay());
			pstmt.setString(21, "1");
			pstmt.setString(22, fclq.getId());
			if(pstmt.executeUpdate()==1) flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
	
	public boolean updateFclQuotatione(FclQuotationBean fclq) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "update fclQuotation set pickupRate=?, stuffingRate=?, where lashingRate=?, ofRate=?, lssebs=?, customsBrokerRate=?, thcRate=?,  otherRate=?, amsRate=?, vgmRate=?, handlingRate=?, usd=?, "
					+ "remarks=?,  oftype=?, carrier=?, tt=?, validity=?, date=?, state =? fclno=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, fclq.getPickupRate());
			pstmt.setInt(2, fclq.getStuffingRate());
			pstmt.setInt(3, fclq.getLashingRate());
			pstmt.setInt(4, fclq.getOfRate());
			pstmt.setInt(5, fclq.getLssebs());
			pstmt.setInt(6, fclq.getCustomsBrokerRate());
			pstmt.setInt(7, fclq.getThcRate());
			pstmt.setInt(8, fclq.getOtherRate());
			pstmt.setInt(9, fclq.getAmsRate());
			pstmt.setInt(10, fclq.getVgmRate());
			pstmt.setInt(11, fclq.getHandlingRate());
			pstmt.setInt(12, fclq.getUsd());

			pstmt.setString(13, fclq.getRemarks());
			pstmt.setString(14, fclq.getOftype());
			pstmt.setString(15, fclq.getCarrier());
			pstmt.setString(16, fclq.getTt());
			pstmt.setString(17, fclq.getValidity());
			pstmt.setString(18, fclq.getDate());
			pstmt.setString(19, fclq.getState());
			
			if(pstmt.executeUpdate()==1) flag = true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
	
	
	
	
	
	
	
	
	//Detail
	public FclQuotationBean getFclQuotationeDetail(int no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		FclQuotationBean quotation = new FclQuotationBean();
		try {
			con = pool.getConnection();
			//fclno =? 말고 외래키 no=? 라고 설정//
			sql = "select * from fclQuotation where no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				quotation.setFclno(rs.getInt("fclno"));
				quotation.setNo(rs.getInt("no"));
				quotation.setPickupRate(rs.getInt("pickupRate"));
				quotation.setStuffingRate(rs.getInt("stuffingRate"));
				quotation.setLashingRate(rs.getInt("lashingRate"));
				quotation.setPickupRate(rs.getInt("pickupRate"));
				quotation.setOfRate(rs.getInt("thcRate"));
				quotation.setOfRate(rs.getInt("ofRate"));
				quotation.setLssebs(rs.getInt("lssebs"));
				quotation.setCustomsBrokerRate(rs.getInt("customsBrokerRate"));
				quotation.setOtherRate(rs.getInt("otherRate"));
				quotation.setAmsRate(rs.getInt("amsRate"));
				quotation.setVgmRate(rs.getInt("vgmRate"));
				quotation.setHandlingRate(rs.getInt("handlingRate"));
				quotation.setWon(rs.getInt("won"));
				quotation.setUsd(rs.getInt("usd"));
				
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
	public Vector<FclQuotationBean> getFclQuotationList(String id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<FclQuotationBean> vlist = new Vector<FclQuotationBean>();
		try {
			con = pool.getConnection();
			sql = "select * from fclQuotation where id=? order by fclno desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				FclQuotationBean quotation = new FclQuotationBean();
				quotation.setFclno(rs.getInt("fclno"));
				quotation.setNo(rs.getInt("no"));
				quotation.setPickupRate(rs.getInt("pickupRate"));
				quotation.setStuffingRate(rs.getInt("stuffingRate"));
				quotation.setLashingRate(rs.getInt("lashingRate"));
				quotation.setPickupRate(rs.getInt("ofRate"));
				quotation.setOfRate(rs.getInt("lssebs"));
				quotation.setLssebs(rs.getInt("thcRate"));
				quotation.setCustomsBrokerRate(rs.getInt("customsBrokerRate"));
				quotation.setOtherRate(rs.getInt("otherRate"));
				quotation.setAmsRate(rs.getInt("amsRate"));
				quotation.setVgmRate(rs.getInt("vgmRate"));
				quotation.setHandlingRate(rs.getInt("handlingRate"));
				quotation.setWon(rs.getInt("won"));
				quotation.setUsd(rs.getInt("usd"));
				
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
	public Vector<FclQuotationBean> getFclQuotationList(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<FclQuotationBean> vlist = new Vector<FclQuotationBean>();
		try {
			con = pool.getConnection();
			sql = "select * from fclQuotation order by fclno desc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				FclQuotationBean quotation = new FclQuotationBean();
				quotation.setFclno(rs.getInt("fclno"));
				quotation.setNo(rs.getInt("no"));
				quotation.setPickupRate(rs.getInt("pickupRate"));
				quotation.setStuffingRate(rs.getInt("stuffingRate"));
				quotation.setLashingRate(rs.getInt("lashingRate"));
				quotation.setPickupRate(rs.getInt("ofRate"));
				quotation.setOfRate(rs.getInt("lssebs"));
				quotation.setCustomsBrokerRate(rs.getInt("customsBrokerRate"));
				quotation.setLssebs(rs.getInt("thcRate"));
				quotation.setOtherRate(rs.getInt("otherRate"));
				quotation.setAmsRate(rs.getInt("amsRate"));
				quotation.setVgmRate(rs.getInt("vgmRate"));
				quotation.setHandlingRate(rs.getInt("handlingRate"));
				quotation.setWon(rs.getInt("won"));
				quotation.setUsd(rs.getInt("usd"));
				
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
