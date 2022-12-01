package domain;

import domain.models.entities.producto.*;
import domain.models.entities.venta.*;
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

	@Autowired
	private ClienteRepository repoCliente;

	@Autowired
	private DireccionRepository repoDireccion;

	@Autowired
	private PublicacionRepository repoPublicacion;

	@Autowired
	private CarritoRepository repoCarrito;

	@Autowired
	private ItemRepository repoItem;



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
				Categoria superior = repoCategoria.save(new Categoria("Prenda superior", LocalDateTime.now()));
				Categoria inferior = repoCategoria.save(new Categoria("Prenda inferior", LocalDateTime.now()));
				Categoria Pijamas = repoCategoria.save(new Categoria("Pijamas", LocalDateTime.now()));
				Categoria accesorios = repoCategoria.save(new Categoria("Accesorios", LocalDateTime.now()));


				// cargas del gestor
				Gestor gestor1 = repoGestor.save(new Gestor("Camilo", "Prieto", "22986789", "camiloprieto@gmail.com", LocalDateTime.now()));

				// Carga de Areas
				AreaDePersonalizacion pecho = repoArea.save(new AreaDePersonalizacion("Pecho", LocalDateTime.now()));
				AreaDePersonalizacion pierna = repoArea.save(new AreaDePersonalizacion("Pierna", LocalDateTime.now()));
				AreaDePersonalizacion costados = repoArea.save(new AreaDePersonalizacion("Costados", LocalDateTime.now()));
				AreaDePersonalizacion frente = repoArea.save(new AreaDePersonalizacion("Frente", LocalDateTime.now()));
				AreaDePersonalizacion delante = repoArea.save(new AreaDePersonalizacion("Delante", LocalDateTime.now()));
				AreaDePersonalizacion todo = repoArea.save(new AreaDePersonalizacion("Todo", LocalDateTime.now()));

				//Tipos
				TipoDePersonalizacion imagen = (TipoDePersonalizacion) repoTipo.save(new TipoDePersonalizacion("Imagen", LocalDateTime.now()));
				TipoDePersonalizacion dibujo = (TipoDePersonalizacion) repoTipo.save(new TipoDePersonalizacion("dibujo", LocalDateTime.now()));
				TipoDePersonalizacion estampa = (TipoDePersonalizacion) repoTipo.save(new TipoDePersonalizacion("estampa", LocalDateTime.now()));


				// carga del producto
				Producto remeraNegra = repoProducto.save(new Producto("Remera", "Negra", 1000F, 10, superior , gestor1, LocalDateTime.now()));
				Producto remeraBlanca = repoProducto.save(new Producto("Remera", "Blanco", 1000F, 10, superior , gestor1, LocalDateTime.now()));
				Producto remeraAzul = repoProducto.save(new Producto("Remera", "Azul", 1000F, 10, superior , gestor1, LocalDateTime.now()));
				Producto remeraGris = repoProducto.save(new Producto("Remera", "Gris", 1000F, 10, superior , gestor1, LocalDateTime.now()));
				Producto remeraBlancayAzul = repoProducto.save(new Producto("Remera", "Blanca y Azul", 1000F, 10, superior , gestor1, LocalDateTime.now()));
				Producto shortAzul = repoProducto.save(new Producto("Short", "Azul", 800F, 6, inferior , gestor1, LocalDateTime.now()));
				Producto shortRojo = repoProducto.save(new Producto("Short", "Rojo", 800F, 6, inferior , gestor1, LocalDateTime.now()));
				Producto shortGris = repoProducto.save(new Producto("Short", "Gris", 800F, 6, inferior , gestor1, LocalDateTime.now()));
				Producto jogginGris = repoProducto.save(new Producto("Joggin", "Gris", 2000F, 15, inferior , gestor1, LocalDateTime.now()));
				Producto pijama1 = repoProducto.save(new Producto("Pijama", "Blanco y azul", 1500F, 20, inferior , gestor1, LocalDateTime.now()));
				Producto pijama2 = repoProducto.save(new Producto("Pijama", "Blanco y rojo", 1500F, 20, inferior , gestor1, LocalDateTime.now()));
				Producto pijama3 = repoProducto.save(new Producto("Pijama", "Blanco y negro", 1500F, 20, inferior , gestor1, LocalDateTime.now()));
				Producto launchera1 = repoProducto.save(new Producto("Launchera", "Gris", 4500F, 20, accesorios , gestor1, LocalDateTime.now()));


				//Cargando posibles personalizaciones
				PosiblePersonalizacion pechoImagen = repoPosible.save(new PosiblePersonalizacion(pecho, imagen, LocalDateTime.now()));
				PosiblePersonalizacion pechoDibujo = repoPosible.save(new PosiblePersonalizacion(pecho, dibujo, LocalDateTime.now()));
				PosiblePersonalizacion pechoEstampa = repoPosible.save(new PosiblePersonalizacion(pecho, estampa, LocalDateTime.now()));
				PosiblePersonalizacion frenteDibujo = repoPosible.save(new PosiblePersonalizacion(frente, dibujo, LocalDateTime.now()));
				PosiblePersonalizacion delanteDibujo = repoPosible.save(new PosiblePersonalizacion(delante, dibujo, LocalDateTime.now()));
				PosiblePersonalizacion frenteEstampa = repoPosible.save(new PosiblePersonalizacion(frente, estampa, LocalDateTime.now()));
				PosiblePersonalizacion piernaEstampa = repoPosible.save(new PosiblePersonalizacion(pierna, estampa, LocalDateTime.now()));
				PosiblePersonalizacion costadosEstampa = repoPosible.save(new PosiblePersonalizacion(costados, estampa, LocalDateTime.now()));
				PosiblePersonalizacion todoEstampa = repoPosible.save(new PosiblePersonalizacion(todo, estampa, LocalDateTime.now()));
				PosiblePersonalizacion delanteEstampa = repoPosible.save(new PosiblePersonalizacion(delante, estampa, LocalDateTime.now()));


				// agregando posibles personalizaciones al producto
				remeraBlanca.agregarPosiblesPersonalizaciones(pechoImagen);
				remeraBlanca.agregarPosiblesPersonalizaciones(delanteDibujo);
				remeraNegra.agregarPosiblesPersonalizaciones(pechoImagen);
				remeraGris.agregarPosiblesPersonalizaciones(frenteDibujo);
				remeraBlancayAzul.agregarPosiblesPersonalizaciones(frenteDibujo);
				shortAzul.agregarPosiblesPersonalizaciones(frenteDibujo);
				shortRojo.agregarPosiblesPersonalizaciones(frenteEstampa);
				shortGris.agregarPosiblesPersonalizaciones(piernaEstampa);
				jogginGris.agregarPosiblesPersonalizaciones(piernaEstampa);
				pijama1.agregarPosiblesPersonalizaciones(delanteDibujo);
				pijama2.agregarPosiblesPersonalizaciones(delanteDibujo);
				pijama3.agregarPosiblesPersonalizaciones(frenteDibujo);
				pijama3.agregarPosiblesPersonalizaciones(pechoEstampa);
				launchera1.agregarPosiblesPersonalizaciones(todoEstampa);
				launchera1.agregarPosiblesPersonalizaciones(delanteEstampa);

				repoProducto.save(remeraBlanca);
				repoProducto.save(remeraNegra);
				repoProducto.save(remeraGris);
				repoProducto.save(remeraBlancayAzul);
				repoProducto.save(shortAzul);
				repoProducto.save(shortRojo);
				repoProducto.save(shortGris);
				repoProducto.save(jogginGris);
				repoProducto.save(pijama1);
				repoProducto.save(pijama2);
				repoProducto.save(pijama3);
				repoProducto.save(launchera1);

				//Cargando tipos de pago
				TipoDePago efectivo = repoTiposDePago.save(new TipoDePago("Efectivo", LocalDateTime.now()));
				TipoDePago tarjeta = repoTiposDePago.save(new TipoDePago("Tarjeta", LocalDateTime.now()));


				//creando vendedor
				Vendedor magic = repoVendedor.save(new Vendedor("Magic Store", "https://d3ugyf2ht6aenh.cloudfront.net/stores/001/130/833/themes/common/logo-865136435-1666206441-46fd0809a84df8043698a23c850e03891666206441.png?0",
						"Juan", "Perez", "juanperez@gmail.com", "22986789", LocalDateTime.now()));
				magic.agregarTipoDePago(efectivo);
				magic.agregarTipoDePago(tarjeta);
				repoVendedor.save(magic);
				Vendedor dadoa = repoVendedor.save(new Vendedor("Dadoa", "https://jbonita.vteximg.com.br/arquivos/logo-dadoa.png?v=637571222097070000",
						"Camila", "Santolla", "camilasantolla@gmail.com", "23784789", LocalDateTime.now()));
				magic.agregarTipoDePago(tarjeta);
				repoVendedor.save(magic);



				// cargando productos personalizados
				ProductoPersonalizado remeraBlancaPersonalizada = repoProductoPersonalizado.save(new ProductoPersonalizado(remeraBlanca, magic, 2200F ,LocalDateTime.now()));
				ProductoPersonalizado remeraBlancaPersonalizada2 = repoProductoPersonalizado.save(new ProductoPersonalizado(remeraBlanca, dadoa, 1900F ,LocalDateTime.now()));
				ProductoPersonalizado remeraNegraPersonalizada1 = repoProductoPersonalizado.save(new ProductoPersonalizado(remeraNegra, dadoa, 1700F ,LocalDateTime.now()));
				ProductoPersonalizado remeraGrisPersonalizada1 = repoProductoPersonalizado.save(new ProductoPersonalizado(remeraGris, magic, 2200F ,LocalDateTime.now()));
				ProductoPersonalizado remeraBlancaYAzulPersonalizada1 = repoProductoPersonalizado.save(new ProductoPersonalizado(remeraBlancayAzul, dadoa, 1600F ,LocalDateTime.now()));
				ProductoPersonalizado shortAzulPersonalizacion1 = repoProductoPersonalizado.save(new ProductoPersonalizado(shortAzul, magic, 1400F ,LocalDateTime.now()));
				ProductoPersonalizado shortRojoPersonalizacion1 = repoProductoPersonalizado.save(new ProductoPersonalizado(shortRojo, dadoa, 1600F ,LocalDateTime.now()));
				ProductoPersonalizado shortGrisPersonalizacion1 = repoProductoPersonalizado.save(new ProductoPersonalizado(shortGris, magic, 1800F ,LocalDateTime.now()));
				ProductoPersonalizado shortJogginPersonalizacion1 = repoProductoPersonalizado.save(new ProductoPersonalizado(jogginGris, dadoa, 1300F ,LocalDateTime.now()));
				ProductoPersonalizado remeraYPantalonAzul = repoProductoPersonalizado.save(new ProductoPersonalizado(pijama1, dadoa, 2400F ,LocalDateTime.now()));
				ProductoPersonalizado remeraYPantalonRojo = repoProductoPersonalizado.save(new ProductoPersonalizado(pijama2, magic, 2300F ,LocalDateTime.now()));
				ProductoPersonalizado remeraYPantalonNegro1 = repoProductoPersonalizado.save(new ProductoPersonalizado(pijama3, dadoa, 2700F ,LocalDateTime.now()));
				ProductoPersonalizado launcheraPersonalizacion1 = repoProductoPersonalizado.save(new ProductoPersonalizado(launchera1, dadoa, 5000F ,LocalDateTime.now()));
				ProductoPersonalizado launcheraPersonalizacion2 = repoProductoPersonalizado.save(new ProductoPersonalizado(launchera1, magic, 5400F ,LocalDateTime.now()));


				//Cargar personalizaciones
				Personalizacion pers1 = repoPersonalizacion.save(new Personalizacion(pechoImagen, "Capitan America", 500F, LocalDateTime.now()));
				Personalizacion pers2 = repoPersonalizacion.save(new Personalizacion(pechoImagen, "Advanger", 700F, LocalDateTime.now()));
				Personalizacion pers3 = repoPersonalizacion.save(new Personalizacion(frenteDibujo, "Spiderman", 600F, LocalDateTime.now()));
				Personalizacion pers4 = repoPersonalizacion.save(new Personalizacion(delanteDibujo, "Advanger", 900F, LocalDateTime.now()));
				Personalizacion pers5 = repoPersonalizacion.save(new Personalizacion(delanteDibujo, "Spiderman", 800F, LocalDateTime.now()));
				Personalizacion pers6 = repoPersonalizacion.save(new Personalizacion(frenteDibujo, "Capitan America", 700F, LocalDateTime.now()));
				Personalizacion pers7 = repoPersonalizacion.save(new Personalizacion(frenteDibujo, "Advanger", 600F, LocalDateTime.now()));
				Personalizacion pers8 = repoPersonalizacion.save(new Personalizacion(frenteEstampa, "Advanger", 800F, LocalDateTime.now()));
				Personalizacion pers10 = repoPersonalizacion.save(new Personalizacion(piernaEstampa, "Marvel", 500F, LocalDateTime.now()));
				Personalizacion pers11 = repoPersonalizacion.save(new Personalizacion(piernaEstampa, "Capitan America", 500F, LocalDateTime.now()));
				Personalizacion pers12 = repoPersonalizacion.save(new Personalizacion(costadosEstampa, "Marvel", 500F, LocalDateTime.now()));
				Personalizacion pers13 = repoPersonalizacion.save(new Personalizacion(delanteDibujo, "Spiderman", 900F, LocalDateTime.now()));
				Personalizacion pers14 = repoPersonalizacion.save(new Personalizacion(delanteDibujo, "Spiderman", 800F, LocalDateTime.now()));
				Personalizacion pers15 = repoPersonalizacion.save(new Personalizacion(frenteDibujo, "Spiderman", 700F, LocalDateTime.now()));
				Personalizacion pers16 = repoPersonalizacion.save(new Personalizacion(pechoEstampa, "Marvel", 500F, LocalDateTime.now()));
				Personalizacion pers17 = repoPersonalizacion.save(new Personalizacion(todoEstampa, "Stitch", 500F, LocalDateTime.now()));
				Personalizacion pers18 = repoPersonalizacion.save(new Personalizacion(delanteEstampa, "Mini", 400F, LocalDateTime.now()));
				Personalizacion pers19 = repoPersonalizacion.save(new Personalizacion(delanteEstampa, "Mickey", 500F, LocalDateTime.now()));

				//Cargar personalizaciones al producto personalizado
				remeraBlancaPersonalizada.agregarPersonalizacion(pers1);
				remeraBlancaPersonalizada.agregarPersonalizacion(pers5);
				remeraBlancaPersonalizada2.agregarPersonalizacion(pers4);
				remeraNegraPersonalizada1.agregarPersonalizacion(pers2);
				remeraGrisPersonalizada1.agregarPersonalizacion(pers3);
				remeraBlancaYAzulPersonalizada1.agregarPersonalizacion(pers6);
				shortAzulPersonalizacion1.agregarPersonalizacion(pers7);
				shortRojoPersonalizacion1.agregarPersonalizacion(pers8);
				shortGrisPersonalizacion1.agregarPersonalizacion(pers10);
				shortJogginPersonalizacion1.agregarPersonalizacion(pers11);
				shortGrisPersonalizacion1.agregarPersonalizacion(pers12);
				remeraYPantalonAzul.agregarPersonalizacion(pers13);
				remeraYPantalonRojo.agregarPersonalizacion(pers14);
				remeraYPantalonNegro1.agregarPersonalizacion(pers15);
				remeraYPantalonNegro1.agregarPersonalizacion(pers16);
				launcheraPersonalizacion1.agregarPersonalizacion(pers17);
				launcheraPersonalizacion2.agregarPersonalizacion(pers18);
				launcheraPersonalizacion2.agregarPersonalizacion(pers19);


				//Cargar Cliente
				Cliente cliente1 = repoCliente.save(new Cliente("Marcelo", "Luna", "15693759","marceloluna@gmail.com", "Marcelo2432", LocalDateTime.now()));

				//Direccion
				Direccion direccion1 = repoDireccion.save(new Direccion("Bonifacini", "2349", "1", "A","San martin", "Buenos Aires", cliente1, LocalDateTime.now()));

			};

		};



}

