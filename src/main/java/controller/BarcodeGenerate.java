package controller;

import com.google.zxing.WriterException;

import dao.DBConnect;
import model.QrCodeComponent;
import service.QrcodeService;

import java.io.IOException;

import java.sql.SQLException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BarcodeGenerate")
public class BarcodeGenerate extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	QrCodeComponent qrcode = new QrCodeComponent();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		
		String qrtext = request.getParameter("qrtext");
		qrcode.setQrtext(qrtext);
		request.setAttribute("input", qrtext);
		
		try {
			DBConnect.insert(qrtext);
			byte[] out = QrcodeService.getQRCodeImage(qrtext, 250,250);
			byte[] encodeBase64 = Base64.getEncoder().encode(out);
			String base64DataString = new String(encodeBase64 , "UTF-8");
			
			request.setAttribute("output", base64DataString);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}