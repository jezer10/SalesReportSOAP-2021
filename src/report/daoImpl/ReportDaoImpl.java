package report.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Iterator;

import javax.jws.WebService;

import report.entity.Producto;
import report.entity.Reporte;
import report.entity.Venta;
import report.utils.Connexion;

@WebService(endpointInterface = "report.daoImpl.ReportDao")
public class ReportDaoImpl implements ReportDao {
	private PreparedStatement ps;
	private ResultSet rs;
	private ResultSetMetaData rsmd;
	private Connection cx;

	@Override
	public String generateReport(String initialDate, String endDate, int idClient) {
		int currentId=0;
		int nextId=0;
		Reporte report = new Reporte();
		try {
			cx = Connexion.getConexion();
			ps = cx.prepareStatement("SELECT v.idventa,v.fecha, p1.nombres cliente,p1.dni,p2.nombres empleado,p.nombre,p.precio, dv.cantidad, dv.subtotal from persona p1 join venta v on (p1.idpersona=v.cliente) JOIN persona p2 on (p2.idpersona=v.empleado) join detalle_venta dv on (v.idventa=dv.idventa) join producto p on (p.idproducto=dv.idproducto) WHERE v.fecha BETWEEN ? and ? and v.cliente=? ");
			ps.setString(1, initialDate);
			ps.setString(2, endDate);
			ps.setInt(3, idClient);
			rs = ps.executeQuery();
			rsmd=rs.getMetaData();
			System.out.println(rsmd.getColumnCount());
			while (rs.next()) {
				nextId=rs.getInt("idventa");
				if(nextId!=currentId) {
					Venta venta= new Venta();
					venta.idventa=nextId;
					venta.fecha=rs.getString("fecha");
					venta.empleado=rs.getString("empleado");
					Producto producto = new Producto();
					producto.nombre=rs.getString("nombre");
					producto.precio=rs.getDouble("precio");
					producto.cantidad=rs.getInt("cantidad");
					producto.subtotal=rs.getDouble("subtotal");
					venta.productos.add(producto);
					report.ventas.add(venta);
				}				
		
				for (int i = 0; i < rsmd.getColumnCount(); i++) {
					System.out.print(rs.getString(i+1)+" ");
				}
				System.out.println("\n");
				
			}
			if(!rs.next()) {
				System.out.println("no quedan registroszZz");
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		
		for (int i = 0; i < report.ventas.size(); i++) {
			System.out.println(report.ventas.get(i).empleado);
		}
		return null;
	}

	@Override
	public String getClients() {
		try {
			cx = Connexion.getConexion();
			ps = cx.prepareStatement("select * from persona");
			rs = ps.executeQuery();
			rsmd=rs.getMetaData();
			System.out.println(rsmd.getColumnCount());
			while (rs.next()) {
				for (int i = 0; i < rsmd.getColumnCount(); i++) {
					System.out.print(rs.getString(i+1)+" ");
				}
				System.out.println("\n");
				
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}


}
