package domain;

import domain.models.entities.producto.*;
import domain.models.entities.venta.Gestor;
import domain.models.entities.venta.TipoDePago;
import domain.models.entities.venta.Vendedor;
import domain.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class TpCapgeminiApplication {


	@Autowired
	private CategoriaRepository repoCategoria;

	@Autowired
	private GestorRepository repoGestor;

	@Autowired
	private ProductoRepository repoProducto;

	@Autowired
	private AreaRepository repoArea;

	@Autowired
	private TipoDePersonalizacionRepository repoTipo;

	@Autowired
	private PersonalizacionPosibleRepository repoPosible;

	@Autowired
	private ProductoPersonalizadoRepository repoProductoPersonalizado;

	@Autowired
	private VendedorRepository repoVendedor;

	@Autowired
	private PersonalizacionRepository repoPersonalizacion;
	@Autowired
	private TipoDePagoRepository repoTiposDePago;


	public static void main(String[] args) {
		SpringApplication.run(TpCapgeminiApplication.class, args);
	}

		@Bean
		public CommandLineRunner init() {

			return (args) -> {
				if (args.length > 0) {
					System.out.println(args[0]);
				}

				// cargas de las distintas categorias
				Categoria superior = repoCategoria.save(new Categoria("Roma superior", LocalDateTime.now()));
				Categoria inferior = repoCategoria.save(new Categoria("Roma inferior", LocalDateTime.now()));
				Categoria calzado = repoCategoria.save(new Categoria("Calzado", LocalDateTime.now()));
				Categoria deportivo = repoCategoria.save(new Categoria("Ropa Deportiva", LocalDateTime.now()));
				Categoria accesorios = repoCategoria.save(new Categoria("Accesorios", LocalDateTime.now()));


				// cargas del gestor
				Gestor gestor1 = repoGestor.save(new Gestor("Camilo", "Prieto", "22986789", "camiloprieto@gmail.com", LocalDateTime.now()));

				// Carga de Areas
				AreaDePersonalizacion espalda = repoArea.save(new AreaDePersonalizacion("Espalda", LocalDateTime.now()));
				AreaDePersonalizacion pecho = repoArea.save(new AreaDePersonalizacion("Pecho", LocalDateTime.now()));
				AreaDePersonalizacion pierna = repoArea.save(new AreaDePersonalizacion("Pierna", LocalDateTime.now()));
				AreaDePersonalizacion brazo = repoArea.save(new AreaDePersonalizacion("Brazo", LocalDateTime.now()));
				AreaDePersonalizacion frente = repoArea.save(new AreaDePersonalizacion("Frente", LocalDateTime.now()));
				AreaDePersonalizacion detras = repoArea.save(new AreaDePersonalizacion("Detras", LocalDateTime.now()));

				//Tipos
				TipoDePersonalizacion imagen = (TipoDePersonalizacion) repoTipo.save(new TipoDePersonalizacion("Imagen", LocalDateTime.now()));
				TipoDePersonalizacion texto = (TipoDePersonalizacion) repoTipo.save(new TipoDePersonalizacion("Texto", LocalDateTime.now()));
				TipoDePersonalizacion imagenYTexto = (TipoDePersonalizacion) repoTipo.save(new TipoDePersonalizacion("Imagen y Texto", LocalDateTime.now()));
				TipoDePersonalizacion emoji = (TipoDePersonalizacion) repoTipo.save(new TipoDePersonalizacion("Emoji", LocalDateTime.now()));



				// carga del producto
				Producto zapatillaBlanca = repoProducto.save(new Producto("Zapatillas", "Blancas", 20000F, 10, calzado , gestor1, LocalDateTime.now()));
				Producto remeraBlanca = repoProducto.save(new Producto("Remera", "Blanco", 3000F, 10, superior , gestor1, LocalDateTime.now()));
				Producto jeanAzul = repoProducto.save(new Producto("Jean", "Azul", 5000F, 10, inferior , gestor1, LocalDateTime.now()));
				Producto camperaNegra = repoProducto.save(new Producto("Campera", "Negra", 10000F, 10, deportivo , gestor1, LocalDateTime.now()));
				Producto gorraRoja = repoProducto.save(new Producto("Gorra", "Roja", 2000F, 10, accesorios , gestor1, LocalDateTime.now()));


				//Cargando posibles personalizaciones
				PosiblePersonalizacion superiorPersonalizacion1 = repoPosible.save(new PosiblePersonalizacion(pecho, imagen, LocalDateTime.now()));
				PosiblePersonalizacion superiorPersonalizacion2 = repoPosible.save(new PosiblePersonalizacion(pecho, texto, LocalDateTime.now()));
				PosiblePersonalizacion superiorPersonalizacion3 = repoPosible.save(new PosiblePersonalizacion(espalda, imagen, LocalDateTime.now()));
				PosiblePersonalizacion superiorPersonalizacion4 = repoPosible.save(new PosiblePersonalizacion(espalda, texto, LocalDateTime.now()));
				PosiblePersonalizacion inferiorPersonalizacion1 = repoPosible.save(new PosiblePersonalizacion(pierna, imagen, LocalDateTime.now()));
				PosiblePersonalizacion inferiorPersonalizacion2 = repoPosible.save(new PosiblePersonalizacion(pierna, texto, LocalDateTime.now()));
				PosiblePersonalizacion inferiorPersonalizacion3 = repoPosible.save(new PosiblePersonalizacion(frente, imagen, LocalDateTime.now()));
				PosiblePersonalizacion calzadoPersonalizacion1 = repoPosible.save(new PosiblePersonalizacion(frente, texto, LocalDateTime.now()));
				PosiblePersonalizacion calzadoPersonalizacion2 = repoPosible.save(new PosiblePersonalizacion(frente, imagen, LocalDateTime.now()));


				// agregando posibles personalizaciones al producto
				remeraBlanca.agregarPosiblesPersonalizaciones(superiorPersonalizacion1);
				remeraBlanca.agregarPosiblesPersonalizaciones(superiorPersonalizacion2);
				camperaNegra.agregarPosiblesPersonalizaciones(superiorPersonalizacion3);
				camperaNegra.agregarPosiblesPersonalizaciones(superiorPersonalizacion4);
				jeanAzul.agregarPosiblesPersonalizaciones(inferiorPersonalizacion1);
				jeanAzul.agregarPosiblesPersonalizaciones(inferiorPersonalizacion2);
				jeanAzul.agregarPosiblesPersonalizaciones(inferiorPersonalizacion3);
				zapatillaBlanca.agregarPosiblesPersonalizaciones(calzadoPersonalizacion2);
				zapatillaBlanca.agregarPosiblesPersonalizaciones(calzadoPersonalizacion1);

				repoProducto.save(remeraBlanca);
				repoProducto.save(camperaNegra);
				repoProducto.save(jeanAzul);
				repoProducto.save(zapatillaBlanca);

				//Cargando tipos de pago
				TipoDePago efectivo = repoTiposDePago.save(new TipoDePago("Efectivo", LocalDateTime.now()));
				TipoDePago tarjeta = repoTiposDePago.save(new TipoDePago("Tarjeta", LocalDateTime.now()));


				//creando vendedor
				Vendedor vendedor1 = repoVendedor.save(new Vendedor("Unilago", "url", "Juan", "Perez", "juanperez@gmail.com", "22986789", LocalDateTime.now()));
				vendedor1.agregarTipoDePago(efectivo);
				vendedor1.agregarTipoDePago(tarjeta);
				repoVendedor.save(vendedor1);



				// cargando productos personalizados
				ProductoPersonalizado remeraBlancaPersonalizada = repoProductoPersonalizado.save(new ProductoPersonalizado(remeraBlanca, vendedor1, 4000F ,LocalDateTime.now()));
				ProductoPersonalizado jeanAzulPersonalizada = repoProductoPersonalizado.save(new ProductoPersonalizado(jeanAzul, vendedor1, 6000F ,LocalDateTime.now()));
				ProductoPersonalizado zapatillaBlancaPersonalizada = repoProductoPersonalizado.save(new ProductoPersonalizado(zapatillaBlanca, vendedor1, 20500F ,LocalDateTime.now()));


				//Cargar personalizaciones
				Personalizacion pers1 = repoPersonalizacion.save(new Personalizacion(superiorPersonalizacion1, "Mickey", 500F, LocalDateTime.now()));
				Personalizacion pers2 = repoPersonalizacion.save(new Personalizacion(superiorPersonalizacion2, "Minnie", 500F, LocalDateTime.now()));
				Personalizacion pers3 = repoPersonalizacion.save(new Personalizacion(inferiorPersonalizacion1, "Mickey", 500F, LocalDateTime.now()));
				Personalizacion pers4 = repoPersonalizacion.save(new Personalizacion(inferiorPersonalizacion2, "Minnie", 500F, LocalDateTime.now()));
				Personalizacion pers5 = repoPersonalizacion.save(new Personalizacion(calzadoPersonalizacion1, "Mickey", 500F, LocalDateTime.now()));

				//Cargar personalizaciones al producto personalizado
				remeraBlancaPersonalizada.agregarPersonalizacion(pers1);
				remeraBlancaPersonalizada.agregarPersonalizacion(pers2);
				jeanAzulPersonalizada.agregarPersonalizacion(pers3);
				jeanAzulPersonalizada.agregarPersonalizacion(pers4);
				zapatillaBlancaPersonalizada.agregarPersonalizacion(pers5);
				repoProductoPersonalizado.save(remeraBlancaPersonalizada);
				repoProductoPersonalizado.save(jeanAzulPersonalizada);
				repoProductoPersonalizado.save(zapatillaBlancaPersonalizada);


			};

		};



}

