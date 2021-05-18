package report.daoimpl;

import javax.jws.WebMethod;
import javax.jws.WebService;

import report.entity.Reporte;

@WebService
public interface ReportDao {
	@WebMethod
	public Reporte generateReport(String initialDate,String endDate, int idClient);
	@WebMethod
	public String getClients();
}
