package domain;

import domain.models.entities.producto.*;
import domain.models.entities.venta.*;
import domain.models.enums.EstadoPublicacion;
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

	private EstadoPublicacion estadoPublicacion;



	public static void main(String[] args) {
		SpringApplication.run(TpCapgeminiApplication.class, args);
	}

/*
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
				AreaDePersonalizacion completo = repoArea.save(new AreaDePersonalizacion("completo", LocalDateTime.now()));

				//Tipos
				TipoDePersonalizacion imagen = (TipoDePersonalizacion) repoTipo.save(new TipoDePersonalizacion("Imagen", LocalDateTime.now()));
				TipoDePersonalizacion dibujo = (TipoDePersonalizacion) repoTipo.save(new TipoDePersonalizacion("dibujo", LocalDateTime.now()));
				TipoDePersonalizacion estampa = (TipoDePersonalizacion) repoTipo.save(new TipoDePersonalizacion("estampa", LocalDateTime.now()));
				TipoDePersonalizacion texto = (TipoDePersonalizacion) repoTipo.save(new TipoDePersonalizacion("text0", LocalDateTime.now()));


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
				Producto pijama4 = repoProducto.save(new Producto("Pijama", "Blanco y negro", 1500F, 20, inferior , gestor1, LocalDateTime.now()));
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
				PosiblePersonalizacion todoEstampa = repoPosible.save(new PosiblePersonalizacion(completo, estampa, LocalDateTime.now()));
				PosiblePersonalizacion delanteEstampa = repoPosible.save(new PosiblePersonalizacion(delante, estampa, LocalDateTime.now()));
				PosiblePersonalizacion delanteTexto = repoPosible.save(new PosiblePersonalizacion(delante, texto, LocalDateTime.now()));


				// agregando posibles personalizaciones al producto
				remeraBlanca.agregarPosiblesPersonalizaciones(pechoImagen);
				remeraBlanca.agregarPosiblesPersonalizaciones(delanteTexto);
				remeraAzul.agregarPosiblesPersonalizaciones(delanteEstampa);
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
				pijama4.agregarPosiblesPersonalizaciones(pechoEstampa);
				launchera1.agregarPosiblesPersonalizaciones(todoEstampa);
				launchera1.agregarPosiblesPersonalizaciones(delanteEstampa);

				repoProducto.save(remeraBlanca);
				repoProducto.save(remeraNegra);
				repoProducto.save(remeraGris);
				repoProducto.save(remeraBlancayAzul);
				repoProducto.save(shortAzul);
				repoProducto.save(remeraAzul);
				repoProducto.save(shortRojo);
				repoProducto.save(shortGris);
				repoProducto.save(jogginGris);
				repoProducto.save(pijama1);
				repoProducto.save(pijama2);
				repoProducto.save(pijama3);
				repoProducto.save(pijama4);
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
				dadoa.agregarTipoDePago(tarjeta);
				repoVendedor.save(dadoa);



				// cargando productos personalizados
				ProductoPersonalizado remeraBlancaPersonalizada = repoProductoPersonalizado.save(new ProductoPersonalizado(remeraBlanca, magic, 2000F ,LocalDateTime.now()));
				ProductoPersonalizado remeraBlancaPersonalizada2 = repoProductoPersonalizado.save(new ProductoPersonalizado(remeraBlanca, dadoa, 1900F ,LocalDateTime.now()));
				ProductoPersonalizado remeraBlancaPersonalizada3 = repoProductoPersonalizado.save(new ProductoPersonalizado(remeraBlanca, magic, 1900F ,LocalDateTime.now()));
				ProductoPersonalizado remeraNegraPersonalizada1 = repoProductoPersonalizado.save(new ProductoPersonalizado(remeraNegra, dadoa, 2200F ,LocalDateTime.now()));
				ProductoPersonalizado remeraGrisPersonalizada1 = repoProductoPersonalizado.save(new ProductoPersonalizado(remeraGris, magic, 2200F ,LocalDateTime.now()));
				ProductoPersonalizado remeraBlancaYAzulPersonalizada1 = repoProductoPersonalizado.save(new ProductoPersonalizado(remeraBlancayAzul, dadoa, 1600F ,LocalDateTime.now()));
				ProductoPersonalizado shortAzulPersonalizacion1 = repoProductoPersonalizado.save(new ProductoPersonalizado(shortAzul, magic, 1400F ,LocalDateTime.now()));
				ProductoPersonalizado shortRojoPersonalizacion1 = repoProductoPersonalizado.save(new ProductoPersonalizado(shortRojo, dadoa, 1600F ,LocalDateTime.now()));
				ProductoPersonalizado shortGrisPersonalizacion1 = repoProductoPersonalizado.save(new ProductoPersonalizado(shortGris, magic, 1800F ,LocalDateTime.now()));
				ProductoPersonalizado shortGrisPersonalizacion2 = repoProductoPersonalizado.save(new ProductoPersonalizado(shortGris, magic, 1800F ,LocalDateTime.now()));
				ProductoPersonalizado shortJogginPersonalizacion1 = repoProductoPersonalizado.save(new ProductoPersonalizado(jogginGris, dadoa, 3000F ,LocalDateTime.now()));
				ProductoPersonalizado remeraYPantalonAzul = repoProductoPersonalizado.save(new ProductoPersonalizado(pijama1, dadoa, 3700F ,LocalDateTime.now()));
				ProductoPersonalizado remeraYPantalonRojo = repoProductoPersonalizado.save(new ProductoPersonalizado(pijama2, magic, 3600F ,LocalDateTime.now()));
				ProductoPersonalizado remeraYPantalonNegro1 = repoProductoPersonalizado.save(new ProductoPersonalizado(pijama3, dadoa, 2000F ,LocalDateTime.now()));
				ProductoPersonalizado remeraYPantalonNegro2 = repoProductoPersonalizado.save(new ProductoPersonalizado(pijama4, magic, 2000F ,LocalDateTime.now()));
				ProductoPersonalizado launcheraPersonalizacion1 = repoProductoPersonalizado.save(new ProductoPersonalizado(launchera1, dadoa, 5500F ,LocalDateTime.now()));
				ProductoPersonalizado launcheraPersonalizacion2 = repoProductoPersonalizado.save(new ProductoPersonalizado(launchera1, magic, 4900F ,LocalDateTime.now()));
				ProductoPersonalizado launcheraPersonalizacion3 = repoProductoPersonalizado.save(new ProductoPersonalizado(launchera1, magic, 5000F ,LocalDateTime.now()));


				//Cargar personalizaciones
				Personalizacion pers1 = repoPersonalizacion.save(new Personalizacion(pechoImagen, "Capitan America", 500F, LocalDateTime.now()));
				Personalizacion pers2 = repoPersonalizacion.save(new Personalizacion(pechoImagen, "Advanger", 700F, LocalDateTime.now()));
				Personalizacion pers3 = repoPersonalizacion.save(new Personalizacion(frenteDibujo, "Spiderman", 600F, LocalDateTime.now()));
				Personalizacion pers4 = repoPersonalizacion.save(new Personalizacion(delanteDibujo, "Advanger", 900F, LocalDateTime.now()));
				Personalizacion pers5 = repoPersonalizacion.save(new Personalizacion(delanteDibujo, "Spiderman", 800F, LocalDateTime.now()));
				Personalizacion pers6 = repoPersonalizacion.save(new Personalizacion(frenteDibujo, "Capitan America", 700F, LocalDateTime.now()));
				Personalizacion pers7 = repoPersonalizacion.save(new Personalizacion(frenteDibujo, "Advanger", 600F, LocalDateTime.now()));
				Personalizacion pers8 = repoPersonalizacion.save(new Personalizacion(frenteEstampa, "Advanger", 800F, LocalDateTime.now()));
				Personalizacion pers9 = repoPersonalizacion.save(new Personalizacion(piernaEstampa, "Marvel", 500F, LocalDateTime.now()));
				Personalizacion pers10 = repoPersonalizacion.save(new Personalizacion(piernaEstampa, "Capitan America", 500F, LocalDateTime.now()));
				Personalizacion pers11 = repoPersonalizacion.save(new Personalizacion(costadosEstampa, "Marvel", 500F, LocalDateTime.now()));
				Personalizacion pers12 = repoPersonalizacion.save(new Personalizacion(delanteDibujo, "Spiderman", 900F, LocalDateTime.now()));
				Personalizacion pers13 = repoPersonalizacion.save(new Personalizacion(delanteDibujo, "Spiderman", 800F, LocalDateTime.now()));
				Personalizacion pers14 = repoPersonalizacion.save(new Personalizacion(frenteDibujo, "Spiderman", 700F, LocalDateTime.now()));
				Personalizacion pers15 = repoPersonalizacion.save(new Personalizacion(pechoEstampa, "Marvel", 500F, LocalDateTime.now()));
				Personalizacion pers16 = repoPersonalizacion.save(new Personalizacion(todoEstampa, "Stitch", 500F, LocalDateTime.now()));
				Personalizacion pers17 = repoPersonalizacion.save(new Personalizacion(delanteEstampa, "Mini", 400F, LocalDateTime.now()));
				Personalizacion pers18 = repoPersonalizacion.save(new Personalizacion(delanteEstampa, "Mickey", 500F, LocalDateTime.now()));

				Personalizacion pers20 = repoPersonalizacion.save(new Personalizacion(delanteTexto, "Spiderman", 500F, LocalDateTime.now()));
				Personalizacion pers27 = repoPersonalizacion.save(new Personalizacion(delanteTexto, "Spiderman", 500F, LocalDateTime.now()));
				Personalizacion pers21 = repoPersonalizacion.save(new Personalizacion(delanteTexto, "Advanger", 500F, LocalDateTime.now()));
				Personalizacion pers25 = repoPersonalizacion.save(new Personalizacion(delanteTexto, "Advanger", 500F, LocalDateTime.now()));
				Personalizacion pers22 = repoPersonalizacion.save(new Personalizacion(delanteTexto, "Capitan America", 500F, LocalDateTime.now()));
				Personalizacion pers23 = repoPersonalizacion.save(new Personalizacion(delanteTexto, "Marvel", 500F, LocalDateTime.now()));
				Personalizacion pers26 = repoPersonalizacion.save(new Personalizacion(delanteTexto, "Marvel", 500F, LocalDateTime.now()));
				Personalizacion pers24 = repoPersonalizacion.save(new Personalizacion(delanteTexto, "Stitch", 500F, LocalDateTime.now()));



				//Cargar personalizaciones al producto personalizado
				remeraBlancaPersonalizada.agregarPersonalizacion(pers1);
				remeraBlancaPersonalizada.agregarPersonalizacion(pers21);
				remeraBlancaPersonalizada2.agregarPersonalizacion(pers4);
				remeraBlancaPersonalizada3.agregarPersonalizacion(pers5);
				remeraNegraPersonalizada1.agregarPersonalizacion(pers2);
				remeraNegraPersonalizada1.agregarPersonalizacion(pers25);
				remeraGrisPersonalizada1.agregarPersonalizacion(pers3);
				remeraBlancaYAzulPersonalizada1.agregarPersonalizacion(pers6);
				shortAzulPersonalizacion1.agregarPersonalizacion(pers7);
				shortRojoPersonalizacion1.agregarPersonalizacion(pers8);
				shortGrisPersonalizacion1.agregarPersonalizacion(pers9);
				shortGrisPersonalizacion1.agregarPersonalizacion(pers23);
				shortJogginPersonalizacion1.agregarPersonalizacion(pers10);
				shortJogginPersonalizacion1.agregarPersonalizacion(pers22);
				shortGrisPersonalizacion2.agregarPersonalizacion(pers11);
				shortGrisPersonalizacion2.agregarPersonalizacion(pers26);
				remeraYPantalonAzul.agregarPersonalizacion(pers12);
				remeraYPantalonAzul.agregarPersonalizacion(pers20);
				remeraYPantalonRojo.agregarPersonalizacion(pers13);
				remeraYPantalonRojo.agregarPersonalizacion(pers27);
				remeraYPantalonNegro1.agregarPersonalizacion(pers14);
				remeraYPantalonNegro2.agregarPersonalizacion(pers15);
				launcheraPersonalizacion1.agregarPersonalizacion(pers16);
				launcheraPersonalizacion1.agregarPersonalizacion(pers24);
				launcheraPersonalizacion2.agregarPersonalizacion(pers17);
				launcheraPersonalizacion3.agregarPersonalizacion(pers18);

				//Guardo productos personalizados con personalizaciones
				repoProductoPersonalizado.save(remeraBlancaPersonalizada);
				repoProductoPersonalizado.save(remeraBlancaPersonalizada2);
				repoProductoPersonalizado.save(remeraBlancaPersonalizada3);
				repoProductoPersonalizado.save(remeraNegraPersonalizada1);
				repoProductoPersonalizado.save(remeraGrisPersonalizada1);
				repoProductoPersonalizado.save(remeraBlancaYAzulPersonalizada1);
				repoProductoPersonalizado.save(shortAzulPersonalizacion1);
				repoProductoPersonalizado.save(shortRojoPersonalizacion1);
				repoProductoPersonalizado.save(shortGrisPersonalizacion1);
				repoProductoPersonalizado.save(shortGrisPersonalizacion2);
				repoProductoPersonalizado.save(shortJogginPersonalizacion1);
				repoProductoPersonalizado.save(remeraYPantalonAzul);
				repoProductoPersonalizado.save(remeraYPantalonRojo);
				repoProductoPersonalizado.save(remeraYPantalonNegro1);
				repoProductoPersonalizado.save(remeraYPantalonNegro2);
				repoProductoPersonalizado.save(launcheraPersonalizacion1);
				repoProductoPersonalizado.save(launcheraPersonalizacion2);
				repoProductoPersonalizado.save(launcheraPersonalizacion3);



				//Cargar Cliente
				Cliente cliente1 = repoCliente.save(new Cliente("Marcelo", "Luna", "15693759","marceloluna@gmail.com", "Marcelo2432", LocalDateTime.now()));

				//Direccion
				Direccion direccion1 = repoDireccion.save(new Direccion("Bonifacini", "2349", "1", "A","San martin", "Buenos Aires", cliente1, LocalDateTime.now()));

				//Cargar publicaciones
				Publicacion publicacion1 = repoPublicacion.save(new Publicacion("Remera personalizada del capitan america para niños", "prenda-superior", "magic","https://d3ugyf2ht6aenh.cloudfront.net/stores/001/130/833/products/remera-avengers-807001-cfbe6c6800b772fd0a16678530419350-1024-1024.png",
						20, estadoPublicacion.ACTIVA, remeraBlancaPersonalizada, LocalDateTime.now()));
				Publicacion publicacion2 = repoPublicacion.save(new Publicacion("Remera negra personalizada Advanger para niños", "prenda-superior", "dadoa","https://d3ugyf2ht6aenh.cloudfront.net/stores/001/130/833/products/remera-avengers-807031-41291466afce7d61af16678504474125-1024-1024.png",
						50, estadoPublicacion.ACTIVA, remeraNegraPersonalizada1, LocalDateTime.now()));
				Publicacion publicacion3 = repoPublicacion.save(new Publicacion("Remera personalizada de tu hombre araña favorito para niños",  "prenda-superior", "magic", "https://d3ugyf2ht6aenh.cloudfront.net/stores/001/130/833/products/80643-n1-52115d310471fbc4ab16678498103741-1024-1024.jpg",
						30, estadoPublicacion.ACTIVA, remeraGrisPersonalizada1, LocalDateTime.now()));
				Publicacion publicacion4 = repoPublicacion.save(new Publicacion("Remera personalizada de Advenger para niños",  "prenda-superior", "dadoa", "https://d3ugyf2ht6aenh.cloudfront.net/stores/001/130/833/products/807621-3b76ca0d301ae4197716611994636893-1024-1024.jpg",
						40, estadoPublicacion.ACTIVA, remeraBlancaPersonalizada2, LocalDateTime.now()));
				Publicacion publicacion5 = repoPublicacion.save(new Publicacion("Remera personalizada de Spiderman para niños", "prenda-superior", "magic", "https://d3ugyf2ht6aenh.cloudfront.net/stores/001/130/833/products/807621-3b76ca0d301ae4197716611994636893-1024-1024.jpg",
						30, estadoPublicacion.ACTIVA, remeraBlancaPersonalizada3, LocalDateTime.now()));
				Publicacion publicacion6 = repoPublicacion.save(new Publicacion("Remera personalizada del Capitan America para niños",  "prenda-superior", "dadoa", "https://d3ugyf2ht6aenh.cloudfront.net/stores/001/130/833/products/807611-7b6b9d844934514e0c16645651725903-1024-1024.jpg",
						50, estadoPublicacion.ACTIVA, remeraBlancaYAzulPersonalizada1, LocalDateTime.now()));
				Publicacion publicacion7 = repoPublicacion.save(new Publicacion("Short personalizado de Advanger para niños", "prenda-inferior", "magic", "https://d3ugyf2ht6aenh.cloudfront.net/stores/001/130/833/products/807791-baab629ecccea918be16680245486487-1024-1024.webp",
						30, estadoPublicacion.ACTIVA, shortAzulPersonalizacion1, LocalDateTime.now()));
				Publicacion publicacion8 = repoPublicacion.save(new Publicacion("Short de Marvel para niños", "prenda-inferior", "magic", "https://d3ugyf2ht6aenh.cloudfront.net/stores/001/130/833/products/80778-short-marvel-avengers1-9e3bdc716703d2213516648947702578-1024-1024.jpg",
						30, estadoPublicacion.ACTIVA, shortGrisPersonalizacion1, LocalDateTime.now()));
				Publicacion publicacion9 = repoPublicacion.save(new Publicacion("Short personalizado de Marvel para niños", "prenda-inferior", "dadoa", "https://d3ugyf2ht6aenh.cloudfront.net/stores/001/130/833/products/80778-short-marvel-avengers1-9e3bdc716703d2213516648947702578-1024-1024.jpg",
						30, estadoPublicacion.ACTIVA, shortRojoPersonalizacion1, LocalDateTime.now()));
				Publicacion publicacion10 = repoPublicacion.save(new Publicacion("Joggin personalizada del Capitan America para niños", "prenda-inferior", "dadoa", "https://d3ugyf2ht6aenh.cloudfront.net/stores/001/130/833/products/80718-jogging-marvel1-844d42da83811c6e9116645491202048-1024-1024.jpg",
						10, estadoPublicacion.ACTIVA, shortJogginPersonalizacion1, LocalDateTime.now()));
				Publicacion publicacion11 = repoPublicacion.save(new Publicacion("Short personalizado de Marvel para niños", "prenda-inferior", "magic", "https://d3ugyf2ht6aenh.cloudfront.net/stores/001/130/833/products/80713-short-avengers-marvel-41-10712e6036751a671c16645482766226-1024-1024.jpg",
						50, estadoPublicacion.ACTIVA, shortGrisPersonalizacion2, LocalDateTime.now()));
				Publicacion publicacion12 = repoPublicacion.save(new Publicacion("Pijama personalizada de Spiderman para niños", "pijamas", "dadoa", "https://d3ugyf2ht6aenh.cloudfront.net/stores/001/130/833/products/pijama-spiderman-806991-9d927429bc795cc92116613548171724-1024-1024.jpg",
						9, estadoPublicacion.ACTIVA, remeraYPantalonAzul, LocalDateTime.now()));
				Publicacion publicacion13 = repoPublicacion.save(new Publicacion("Pijama de Spiderman para niños", "pijamas", "magic", "https://d3ugyf2ht6aenh.cloudfront.net/stores/001/130/833/products/80698-pijama-spiderman-marvel1-c6111a4f5ed29e881316648953097529-1024-1024.jpg",
						29, estadoPublicacion.ACTIVA, remeraYPantalonRojo, LocalDateTime.now()));
				Publicacion publicacion14 = repoPublicacion.save(new Publicacion("Pijama con figura de Spiderman para niños", "pijamas", "dadoa", "https://d3ugyf2ht6aenh.cloudfront.net/stores/001/130/833/products/806971-b56754e7b32fa8d1f216619753524814-1024-1024.jpg",
						40, estadoPublicacion.ACTIVA, remeraYPantalonNegro1, LocalDateTime.now()));
				Publicacion publicacion15 = repoPublicacion.save(new Publicacion("Pijama personalizada de Marvel para niños", "pijamas", "magic", "https://d3ugyf2ht6aenh.cloudfront.net/stores/001/130/833/products/807741-9533b3e4f37caa01b316612002482358-1024-1024.png",
						46, estadoPublicacion.ACTIVA, remeraYPantalonNegro2, LocalDateTime.now()));
				Publicacion publicacion16 = repoPublicacion.save(new Publicacion("Launchera Stitch para niños", "accesorios", "dadoa", "https://d3ugyf2ht6aenh.cloudfront.net/stores/001/130/833/products/d_nq_np_2x_724699-mla49622239719_042022-f1-a34fd8d84e117518dd16697499818738-1024-1024.webp",
						23, estadoPublicacion.ACTIVA, launcheraPersonalizacion1, LocalDateTime.now()));
				Publicacion publicacion17 = repoPublicacion.save(new Publicacion("Launchera Mini para niños", "accesorios", "magic", "https://d3ugyf2ht6aenh.cloudfront.net/stores/001/130/833/products/d_nq_np_2x_775945-mla51471832256_092022-f1-daf3370151bc65671816697491265434-1024-1024.webp",
						23, estadoPublicacion.ACTIVA, launcheraPersonalizacion2, LocalDateTime.now()));
				Publicacion publicacion19 = repoPublicacion.save(new Publicacion("Launchera Mickey para niños", "accesorios", "magic", "https://d3ugyf2ht6aenh.cloudfront.net/stores/001/130/833/products/d_nq_np_2x_958145-mla51471629226_092022-f1-4791909ffb405fbc3716697484486436-1024-1024.webp",
						23, estadoPublicacion.ACTIVA, launcheraPersonalizacion3, LocalDateTime.now()));

			};

		};

*/

}

