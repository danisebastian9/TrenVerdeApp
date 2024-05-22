package TrenVerdeApp.TrenVerdeApp.controller;

import TrenVerdeApp.TrenVerdeApp.entity.Usuario;
import TrenVerdeApp.TrenVerdeApp.service.IUsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IUsuarioService usuarioService;

    @Test
    public void testListarUsuarios() throws Exception {
        Usuario usuario1 = new Usuario("user1", "password1");
        Usuario usuario2 = new Usuario("user2", "password2");

        when(usuarioService.listarUsuarios()).thenReturn(Arrays.asList(usuario1, usuario2));

        mockMvc.perform(get("/api/usuarios"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[{\"username\":\"user1\",\"password\":\"password1\"},{\"username\":\"user2\",\"password\":\"password2\"}]"));
    }

    @Test
    public void testBuscarUsuarioPorId() throws Exception {
        Usuario usuario = new Usuario("user1", "password1");
        when(usuarioService.buscarUsuarioPorId(1)).thenReturn(usuario);

        mockMvc.perform(get("/api/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"username\":\"user1\",\"password\":\"password1\"}"));
    }

    @Test
    public void testBuscarUsuarioPorUsername() throws Exception {
        Usuario usuario = new Usuario("user1", "password1");
        when(usuarioService.buscarUsuarioPorUsername("user1")).thenReturn(usuario);

        mockMvc.perform(get("/api/usuarios/username/user1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"username\":\"user1\",\"password\":\"password1\"}"));
    }
}
