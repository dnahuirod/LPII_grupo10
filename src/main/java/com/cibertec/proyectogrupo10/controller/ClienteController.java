package com.cibertec.proyectogrupo10.controller;
import com.cibertec.proyectogrupo10.model.Cliente;
import com.cibertec.proyectogrupo10.service.ClienteService;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/clientes")
@AllArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;
    private final EntityManager entityManager;

    @GetMapping("/listarClientes")
    public String verPaginaInicio(Model model) {
        List<Cliente> listaClientes = clienteService.listarClientes();
        model.addAttribute("listaClientes", listaClientes);
        return "clientes/clientes"; // Asegúrate de tener esta vista
    }

    @GetMapping("/nuevoCliente")
    public String nuevoCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clientes/nuevoCliente"; // Asegúrate de tener esta vista
    }

    @PostMapping("/guardarCliente")
    public String guardarCliente(@ModelAttribute("cliente") Cliente cliente) {
        clienteService.guardarCliente(cliente);
        return "redirect:/clientes/listarClientes";
    }

    @GetMapping("/actualizarCliente/{id}")
    public String actualizarCliente(@PathVariable("id") Integer id, Model model) {
        Cliente cliente = clienteService.obtenerClientePorId(id);
        model.addAttribute("cliente", cliente);
        return "clientes/actualizarCliente"; // Asegúrate de tener esta vista
    }

    @GetMapping("/eliminarCliente/{id}")
    public String eliminarCliente(@PathVariable("id") Integer id) {
        clienteService.eliminarCliente(id);
        return "redirect:/clientes/listarClientes";
    }

    @GetMapping("reporteClientes")
    public void reporteVendedores(HttpServletResponse response) throws JRException, SQLException, IOException {
        // Obtén la conexión JDBC desde el EntityManager
        Session session = entityManager.unwrap(Session.class);
        Connection conn = session.doReturningWork(connection -> connection.unwrap(Connection.class));

        // Cargar el reporte .jasper
        InputStream jasperStream = this.getClass().getResourceAsStream("/reportes/rptClientes.jasper.jasper");
        Map<String, Object> params = new HashMap<>();

        // Cargar el reporte
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);

        // Llenar el reporte con la conexión JDBC obtenida
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, conn);

        // Configuración para devolver el reporte PDF en la respuesta HTTP
        response.setContentType("application/x-pdf");
        response.setHeader("Content-disposition", "inline; filename=clientes_report.pdf");

        // Enviar el reporte como respuesta
        final OutputStream outputStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

        // Cerrar la conexión
        conn.close();
    }
}
