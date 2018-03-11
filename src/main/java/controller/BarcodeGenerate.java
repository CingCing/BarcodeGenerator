package controller;

import com.google.zxing.WriterException;

import dao.DBConnect;
import model.QrCodeComponent;
import service.QrCodeImage;

import java.io.IOException;

import java.io.OutputStream;
import java.sql.SQLException;

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
		String text = qrcode.getQrtext();
		System.out.println("xaxa: " + text);
		
		byte[] out;
		try {
			DBConnect.insert(text);
			out = QrCodeImage.getQRCodeImage(qrtext, 250,250);
			response.setContentType("image/png");
			response.setContentLength(out.length);
//			request.getRequestDispatcher("index.jsp").include(request, response);		
			
			OutputStream outStream = response.getOutputStream();
//			PrintWriter outStream = response.getWriter();
					
			outStream.write(out);

			outStream.flush();
			outStream.close();
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}