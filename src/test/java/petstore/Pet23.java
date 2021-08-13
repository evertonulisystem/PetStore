package petstore;

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class Pet23 {

    String uri = "https://petstore.swagger.io/v2/pet";

    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }
    @Test
    public void incluirPet(){
        String jsonBody = null;
        try {
            jsonBody = lerJson("db/pet1.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

        given()
                .contentType("application/json") //comum em api rest - antes era tet/xml
                .log().all()
                .body(jsonBody)
        .when()
                .post(uri)

        .then()
                .log().all()
                .statusCode(200)
        //noovo itens anaxo
                .body("name", is("Snoopy6"))
                .body("status", is("available"))

        ;



    }
}
