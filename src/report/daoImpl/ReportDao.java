package report.daoImpl;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface ReportDao {
	@WebMethod
	public String generateReport(String initialDate,String endDate, int idClient);
	@WebMethod
	public String getClients();
}
