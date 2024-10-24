package com.cibertec.proyectogrupo10.controller;

import com.cibertec.proyectogrupo10.model.Usuario;
import com.cibertec.proyectogrupo10.service.UsuarioService;
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
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final EntityManager entityManager;

    @GetMapping("/listarUsuarios")
    public String verPaginaInicio(Model model) {
        List<Usuario> listaUsuarios = usuarioService.listarUsuarios();
        model.addAttribute("listaUsuarios", listaUsuarios);
        return "usuarios/usuarios"; // Asegúrate de tener esta vista
    }

    @GetMapping("/nuevoUsuario")
    public String nuevoUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuarios/nuevoUsuario"; // Asegúrate de tener esta vista
    }

    @PostMapping("/guardarUsuario")
    public String guardarUsuario(@ModelAttribute("usuario") Usuario usuario) {
        usuarioService.guardarUsuario(usuario);
        return "redirect:/usuarios/listarUsuarios";
    }

    @GetMapping("/actualizarUsuario/{id}")
    public String actualizarUsuario(@PathVariable("id") Integer id, Model model) {
        Usuario usuario = usuarioService.obtenerUsuarioPorId(id);
        model.addAttribute("usuario", usuario);
        return "usuarios/actualizarUsuario"; // Asegúrate de tener esta vista
    }

    @GetMapping("/eliminarUsuario/{id}")
    public String eliminarUsuario(@PathVariable("id") Integer id) {
        usuarioService.eliminarUsuario(id);
        return "redirect:/usuarios/listarUsuarios";
    }


    @GetMapping("/reporteUsuarios")
    public void reporteUsuarios(HttpServletResponse response) throws JRException, SQLException, IOException {
        // Obtén la conexión JDBC desde el EntityManager
        Session session = entityManager.unwrap(Session.class);
        Connection conn = session.doReturningWork(connection -> connection.unwrap(Connection.class));

        // Cargar el reporte .jasper
        InputStream jasperStream = this.getClass().getResourceAsStream("/reportes/rptUsuarios.jasper");
        Map<String, Object> params = new HashMap<>();

        // Cargar el reporte
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);

        // Llenar el reporte con la conexión JDBC obtenida
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, conn);

        // Configuración para devolver el reporte PDF en la respuesta HTTP
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=usuarios_report.pdf");

        // Enviar el reporte como respuesta
        try (OutputStream outputStream = response.getOutputStream()) {
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        } finally {
            // Cerrar la conexión
            conn.close();
        }
    }
}
