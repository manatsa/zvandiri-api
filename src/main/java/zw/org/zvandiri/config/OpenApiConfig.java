package zw.org.zvandiri.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * @author manatsachinyeruse@gmail.com
 */


@OpenAPIDefinition(
        info = @Info(
                title="Zvandiri Rest Api",
                version = "1.0.0",
                description = "The Zvandiri Api to connect from Power BI",
                contact = @Contact(
                        name = "Manatsa Chinyeruse - DB Officer",
                        url = "https://www.africaid-zvandiri.org/contact-us",
                        email = "manatsachinyeruse@gmail.com"),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0.html")
        ),
        servers = { @Server(url="http://localhost:8888/zvandiri-api",
                description = "Default Server")},
        tags = {
                @Tag(name="Power Bi", description="Rest endpoint for Power BI connection."),
                @Tag(name="api", description="A general api for reading data only ")
        }
                )
public class OpenApiConfig {
}
