import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

/*
    Java coding standarts - sounds as abstract as AbstractClass in java (some things usually the same,
     other things can be different on every project)... I will review code below using google-java standarts

     Reference -  https://google.github.io/styleguide/javaguide.html


     Also I wont touch access modifiers cause lack of content
 */

public class User {
//    Logger logger = Logger.getLogger(User.class.toString()); // probably this is not so important, dont want to pull dependency :)

    public String name; // non-constant fields should be in lowerCamelCase (from me - you already have class User, why append that again for field name?)

    public String surname; // non-constant fields should be in lowerCamelCase (from me - you already have class User, why append that again for field name?)

    public User() {
    }

    // (from me - you already have class User, why append that again for field name?)
    public User(String name) {
        this.name = name;
    }

    // method parameters should be in lowerCamelCase (from me - you already have class User, why append that again for field name?)
    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    // (from me - you already have class User, why append that again for field name?)
    // added 'From' suffix, cause method takes as parameter source, from which takes data
    Collection<Hobby> getHobbiesFrom(DummyDao dao) {
//        logger.debug("Get " + USER_NAME + "'s hobbies from database"); // just comment to avoid compilation error
        List<Hobby> hobbiesList = dao.getHobbies(this.name);
        return new TreeSet<>(hobbiesList);
    }


    double calculateHobbyCosts(DummyDao dao) {
//        Collection<Hobby> userHobbies = this.getHobbiesFrom(dao); // using 'this' may be owerkill for all cases, but it simplifies code and helps avoid errors

//        List<Double> allCosts = new ArrayList<>();
//        for (Hobby hobby : userHobbies) {
//            allCosts.add(dao.getHobbyAverageCost(hobby));
//        }

//        double totalCosts = 0;
//        for (Double cost : allCosts) {
//            totalCosts = totalCosts + cost;
//        }
//        return totalCosts;

        // all code above can be simplified (also with performance and space pros) with following:
        return this.getHobbiesFrom(dao).stream()
                .map(dao::getHobbyAverageCost)
                .mapToDouble(d -> d) // still, this sucks...probably there is some better way
                .sum();
    }


    // this equals does not override Object.equals()...wont touch it, may be that was made by will?
    public boolean equals(User user) {
        // using equals operator for strings can be tricky cause of java StringPool
        // so better to use 'equals' method instead
        // and also this method wont pass null-check
//        return user.USER_NAME == USER_NAME && user.USER_SURNAME == USER_SURNAME;

        return user != null && this.name.equals(user.name) && this.surname.equals(user.surname);
    }

    public class Hobby {
        String name;

        public Hobby(String name) {
            this.name = name;
        }

        public Hobby() {
        }
    }

    // having this class as part of User class seems like an architecture error for me...
    // if its just a source, maybe it should be in completely other package?
    // if it strongly connected with User class only, maybe it should be part of User class
    // not the separate one ?
    public abstract class DummyDao {

        abstract List<User.Hobby> getHobbies(String name);

        abstract Double getHobbyAverageCost(User.Hobby name);
    }
}
