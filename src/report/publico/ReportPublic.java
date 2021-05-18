package report.publico;

import javax.xml.ws.Endpoint;

import report.daoimpl.ReportDaoImpl;

public class ReportPublic {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Endpoint.publish("http://localhost:5000/soap/ventas/reporte", new ReportDaoImpl());
		System.out.println("Executing!");
	}
}
