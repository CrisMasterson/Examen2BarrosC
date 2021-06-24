package com.cristhian.barros.Examen.resources;
import com.cristhian.barros.Examen.entities.Productos;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductosResourceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    private RestService restService;
    private Productos productos;

    @Before
    public void before() {
        productos = new Productos();
        this.productos.setModelo("GPU RTX3090");
        this.productos.setMarca("Nvidia");
        this.productos.setPrice(1000);
    }

    @Test
    public void getAllProductos() {
        String json = restService
                .restBuilder(new RestBuilder<String>().clazz(String.class))
                .path(ProductosResource.PRODUCTOS).get().build();
        System.out.println(json);
    }

    @Test
    public void getById() {
        String json = restService
                .restBuilder(new RestBuilder<String>().clazz(String.class))
                .path(ProductosResource.PRODUCTOS).path(ProductosResource.ID).expand(1).get().build();
        System.out.println(json);
    }



    @Test
    public void createProduct() {
        String json = restService
                .restBuilder(new RestBuilder<String>().clazz(String.class))
                .path(ProductosResource.PRODUCTOS).body(this.productos).post().build();
        System.out.println(json);

    }

    @Test
    public void editProduct() {
        this.productos.setMarca("RADEON RX6000");
        this.productos.setModelo("AMD");
        this.productos.setId(1);
        this.productos.setPrice(750.00);
        String json = restService
                .restBuilder(new RestBuilder<String>().clazz(String.class))
                .path(ProductosResource.PRODUCTOS).path(ProductosResource.ID)
                .expand(1).body(productos).put().build();
        System.out.println(json);
    }

    @Test
    public void deleteProduct() {
        String json = restService
                .restBuilder(new RestBuilder<String>().clazz(String.class))
                .path(ProductosResource.PRODUCTOS).path(ProductosResource.ID).expand(1).delete().build();
        System.out.println(json);
    }
}