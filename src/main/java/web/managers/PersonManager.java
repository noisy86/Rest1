package web.managers;

import web.entities.PersonEntity;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;

@ApplicationScoped
public class PersonManager {
    private ArrayList<PersonEntity> personEntities = new ArrayList<>();

    public ArrayList<PersonEntity> getPeople(){ return personEntities; }

    public boolean create(PersonEntity personDetail) {
        if(personDetail.getAge() < 0 || personDetail.getAge() > 100)
            return false;
        int generatedId = (int) (Math.random()*(100 +1));
        if (personCheck(generatedId)){
            personDetail.setId(generatedId);
        }
        return true;
    }
    public PersonEntity getPerson (int id){
        return personEntities.stream()
                .filter(personDetailStream -> id == personDetailStream.getId())
                .findAny()
                .orElse(null);
    }
    public boolean removePerson(int id){
        return  personEntities.remove(getPerson(id));
    }
    public boolean personCheck(int id) {
        for (int i = 0; i < 100; i++){
            if (id != personEntities.get(id).id) {
                return false;
            }
        }
        return true;
    }

}
