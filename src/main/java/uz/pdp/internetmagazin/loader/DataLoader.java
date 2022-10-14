package uz.pdp.internetmagazin.loader;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.internetmagazin.entity.Role;
import uz.pdp.internetmagazin.entity.RoleEnum;
import uz.pdp.internetmagazin.entity.User;
import uz.pdp.internetmagazin.repository.RoleRepository;
import uz.pdp.internetmagazin.repository.UserRepository;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@RequiredArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @Value("${spring.sql.init.mode}")
    private String mode;


    @Override
    public void run(String... args) throws Exception {
        if (mode.equals("always")){
            List<Role> roleList = Arrays.asList(new Role(RoleEnum.ROLE_ADMIN), new Role(RoleEnum.ROLE_MANAGER), new Role(RoleEnum.ROLE_USER));
            roleRepository.saveAll(roleList);

            List<User> userList = Arrays.asList(
                    new User("admin", passwordEncoder.encode("root123"), "Admin", roleRepository.findByName(RoleEnum.ROLE_ADMIN)),
                    new User("manager",  passwordEncoder.encode("root123"), "Manager", roleRepository.findByName(RoleEnum.ROLE_MANAGER))
            );
            userRepository.saveAll(userList);

            try (InputStream input = DataLoader.class.getClassLoader().getResourceAsStream("application.properties")) {

                Properties prop = new Properties();

                if (input == null) {
                    System.err.println("Sorry, unable to find config.properties");
                    return;
                }

                //load a properties file from class path, inside static method
                prop.load(input);

                //get the property value and print it out
                System.out.println(prop.getProperty("spring.jpa.hibernate.ddl-auto"));
                System.out.println(prop.getProperty("spring.sql.init.mode"));

                prop.setProperty("spring.jpa.hibernate.ddl-auto","update");
                prop.setProperty("spring.sql.init.mode","never");

                FileOutputStream out = new FileOutputStream("src/main/resources/application.properties");
                prop.store(out, null);
                out.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

    }
}
