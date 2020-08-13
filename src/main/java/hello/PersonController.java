package hello;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * This Database works and you can now RESTfully interact with things in the database.
 *
 * Once all the initial setup is complete this is how you prep Heroku to deal with this project
 *
 * Go to CMD and do the following
 *
 * Create a heroku project if you don't already have one
 * $ heroku create --stack cedar
 *
 * IMPORTANT: Set the environment variables
 * $ heroku config:set SPRING_CLOUD_APP_NAME={Heroku Given App Name}
 *
 * Create a Postgres DB if you don't already have one
 * $ heroku addons:add heroku-postgresql:hobby-dev
 *
 * Deploy project
 * $ git push heroku master
 *
 */
@RestController
public class PersonController {

    @Autowired
    PersonRepo repo;

    @RequestMapping("/test")
    public Person testJSON() {
        return new Person("Text Value");
    }

    @RequestMapping("/people")
    public ArrayList<Person> getPeopleListJSON() {
        ArrayList<Person> peopleList = new ArrayList<>();

        for (Person person : repo.findAll()) {
            peopleList.add(person);
        }

        return peopleList;
    }

    @RequestMapping("/add/{person}")
    public ArrayList<Person> addPersonToDB(@PathVariable String person) {
        repo.save(new Person(person));
        ArrayList<Person> result = new ArrayList<>();
        for (Person ans : repo.findByNameIgnoreCase(person)) {
            result.add(ans);
        }
        return result;
    }

    @RequestMapping("/get/{person}")
    public ArrayList<Person> getPersonFromDB(@PathVariable String person) {
        ArrayList<Person> result = new ArrayList<>();
        for (Person ans : repo.findByNameIgnoreCase(person)) {
            result.add(ans);
        }
        return result;
    }

}