package report.client;

import report.daoimpl.ReportDaoImpl;

public class ClientService {
	public static void main(String[] args) {
		ReportDaoImpl rdi = new ReportDaoImpl();
		rdi.generateReport("2021-04-08", "2021-05-03", 2);
	}
}
