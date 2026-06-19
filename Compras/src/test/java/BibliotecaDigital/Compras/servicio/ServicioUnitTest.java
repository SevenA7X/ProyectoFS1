package BibliotecaDigital.Compras.servicio;

import BibliotecaDigital.Compras.Modelo.Compra;
import BibliotecaDigital.Compras.Repositorio.Repositorio;
import BibliotecaDigital.Compras.Servicio.Servicio;
import BibliotecaDigital.Compras.dto.ComprasDTO;
import BibliotecaDigital.Compras.dto.JuegoValidacionDTO;
import BibliotecaDigital.Compras.dto.PagosDTO;
import BibliotecaDigital.Compras.dto.UsuarioDTO;
import BibliotecaDigital.Compras.client.UsuarioFeingClient;
import BibliotecaDigital.Compras.client.CatalogoFeignClient;
import BibliotecaDigital.Compras.client.PagosFeignClient;
import BibliotecaDigital.Compras.client.LicenciasFeignClient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class ServicioUnitTest {

    @Mock
    private Repositorio repositorio;

    @Mock
    private UsuarioFeingClient usuarioFeignClient;

    @Mock
    private CatalogoFeignClient catalogoFeignClient;

    @Mock
    private PagosFeignClient pagoFeingClient;

    @Mock
    private LicenciasFeignClient licenciasFeignClient;

    @InjectMocks
    private Servicio servicio; 

    private ComprasDTO comprasDTO;
    private UsuarioDTO usuarioComprador;
    private JuegoValidacionDTO juegoCatalogo;
    private Compra compraEntidadGuardada;

    @BeforeEach
    void setUp() {
        comprasDTO = new ComprasDTO();
        comprasDTO.setUsuarioID(1L);
        comprasDTO.setVideojuegoID(100L);
        comprasDTO.setEstado_orden("COMPLETADA");
        comprasDTO.setFecha_compra(LocalDate.now());


        usuarioComprador = new UsuarioDTO();
        usuarioComprador.setNombreUsuario("matias");
        usuarioComprador.setRol("CLIENTE");

        juegoCatalogo = new JuegoValidacionDTO();
        juegoCatalogo.setTitulo("Zelda");
        juegoCatalogo.setPrecio(59.99);


        compraEntidadGuardada = new Compra();
        compraEntidadGuardada.setCompraID(555L); 
        compraEntidadGuardada.setUsuarioID(1L);
        compraEntidadGuardada.setVideojuegoID(100L);
        compraEntidadGuardada.setEstado_orden("COMPLETADA");
        compraEntidadGuardada.setFecha_compra(LocalDate.now());
    }


    @Test
    void cuandoGuardarCompraValida_entoncesProcesaExitosamente() {
        // ==========================================
        // GIVEN
        // ==========================================
        Mockito.when(usuarioFeignClient.obtenerUsuarioPorId(1L)).thenReturn(usuarioComprador);
        Mockito.when(catalogoFeignClient.obtenerJuegoPorId(100L)).thenReturn(juegoCatalogo);
        
        PagosDTO pagoEsperado = new PagosDTO();
        pagoEsperado.setPagoID(777L);
        Mockito.when(pagoFeingClient.crearPago(any(PagosDTO.class)))
               .thenReturn(ResponseEntity.ok(pagoEsperado));
               
        Mockito.when(repositorio.save(any(Compra.class))).thenReturn(compraEntidadGuardada);

        // ==========================================
        // WHEN
        // ==========================================
        ComprasDTO resultado = servicio.save(comprasDTO);

        // ==========================================
        // THEN
        // ==========================================
        assertNotNull(resultado);
        assertEquals(555L, resultado.getCompraID());
        assertEquals("COMPLETADA", resultado.getEstado_orden());
        

        Mockito.verify(licenciasFeignClient, Mockito.times(1)).generarLicenciaPorCompra(any(ComprasDTO.class));
        Mockito.verify(repositorio, Mockito.times(1)).save(any(Compra.class));
    }


    @Test
    void cuandoUsuarioEsModerador_entoncesLanzaExcepcionYNoGuarda() {
        // ==========================================
        // GIVEN
        // ==========================================
        usuarioComprador.setRol("MODERADOR"); 
        Mockito.when(usuarioFeignClient.obtenerUsuarioPorId(1L)).thenReturn(usuarioComprador);

        // ==========================================
        // WHEN & THEN
        // ==========================================
        IllegalArgumentException excepcion = assertThrows(IllegalArgumentException.class, () -> {
            servicio.save(comprasDTO);
        });

        assertEquals("No se puede procesar la compra: Los moderadores no están autorizados a comprar.", excepcion.getMessage());
        
        Mockito.verifyNoInteractions(catalogoFeignClient);
        Mockito.verifyNoInteractions(repositorio);
    }
}