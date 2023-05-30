package com.fse4.search.azure.cosmosdb.FSE4AzureSearchCosmosdb.service;

//import com.google.common.collect.ComparisonChain;

import com.fse4.search.azure.cosmosdb.FSE4AzureSearchCosmosdb.entity.Associate;
import com.fse4.search.azure.cosmosdb.FSE4AzureSearchCosmosdb.entity.Skills;
import com.fse4.search.azure.cosmosdb.FSE4AzureSearchCosmosdb.exception.AssociateNotfoundException;
import com.fse4.search.azure.cosmosdb.FSE4AzureSearchCosmosdb.exception.AssociateNotfoundForGivenSkillException;
import com.fse4.search.azure.cosmosdb.FSE4AzureSearchCosmosdb.model.Profile;
import com.fse4.search.azure.cosmosdb.FSE4AzureSearchCosmosdb.repo.AssociateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AssociateService {

    @Autowired
    private AssociateRepository repo;

    private static final String RATING_SEARCH_VALUE = "10";


    public Associate getAssociateById(final String associateId){
        Associate data = null;
        data = repo.findByAssociateid(associateId);
        if(null == data){
            log.warn("Associate data not found with given ID");
            throw new AssociateNotfoundException();
        }else{
            log.info("Associate data found");
        }
        return data;
    }


    public List<Associate> getAllAssociates (){
        System.out.println("######## Service Layer method - getAllAssociates");
        List<Associate> associateList =  repo.findAll();
        System.out.println("######## Service Layer method - JUST After repo.findAll()");


        if(null == associateList){
            log.warn("Associate data not loaded");
            throw new AssociateNotfoundException();
        }else{
            log.info("Associate data found");
        }
        return associateList;
        //return sortAsPerExpertiseDescending(associateList);
    }


//    private List<Associate> sortAsPerExpertiseDescending(List<Associate> associateList){
//
//        for(Associate associateObj : associateList){
//
//            System.out.println("\n\n\n\n ##################");
//            System.out.println("New Row Data = " + associateObj.getName());
//            List<Skills> unSortedSkillsList = associateObj.getTechnical_skills();
//            unSortedSkillsList.stream().forEach(skillObj -> System.out.println(skillObj.getTopic() + " - " + skillObj.getRating()));
//
//            Comparator<Skills> skillsComparatorLEx = (s1, s2) -> s1.getRating().compareTo(s2.getRating());
//
//            List<Skills> sortedSkillList = unSortedSkillsList.stream().sorted(skillsComparatorLEx).collect(Collectors.toList());
//
//            System.out.println("---------- AFTER SORT OPERATION -------------------");
////            // print new list to console using forEach()
//            sortedSkillList.stream().forEach(skillObj -> System.out.println(skillObj.getTopic() + " - " + skillObj.getRating()));
//
//            System.out.println("\n\n\n----------END OF DATA----------------- \n\n\n");
//            associateObj.setTechnical_skills(sortedSkillList);
//        }
//        return associateList;
//
//
//
//    }




//    public List<Associate> getAllAssociatesOrderBySort (final String orderby, final String sort){
//
//
//        List<Associate> associateList = new ArrayList<Associate>();
//        if(orderby.equals("name")&& sort.equals("asc")){
//           associateList = repo.findByOrderByNameAsc();
//        }
//        if(orderby.equals("name")&& sort.equals("desc")){
//            associateList = repo.findByOrderByNameDesc();
//        }
//        if(orderby.equals("associateid")&& sort.equals("asc")){
//            associateList = repo.findByOrderByAssociateidAsc();
//        }
//        if(orderby.equals("associateid")&& sort.equals("desc")){
//            associateList = repo.findByOrderByAssociateidDesc();
//        }
//        if(null == associateList){
//            log.error("Associate data not loaded");
//            throw new AssociateNotfoundException();
//        }else{
//            log.info("Associate data found with given ID");
//        }
//        return associateList;
//    }
//
//    public Associate getAssociateByID (final String associateId){
//        Associate associate =  repo.findByAssociateid(associateId);
//        if(null == associate){
//            log.error("Associate data not found with given ID");
//            throw new AssociateNotfoundException();
//        }else{
//            log.info("Associate data found with given ID");
//        }
//        return associate;
//    }

//    public List<Associate> getAssociatesByName(final String nameFromUI){
//        List<Associate> filteredAssociateList = new ArrayList<Associate>();
//        StringBuilder nameInitials = new StringBuilder();
//        if(nameFromUI.length()>4){
//            filteredAssociateList =  repo.getAssociatesByName(nameFromUI);
//            if(null == filteredAssociateList){
//                log.warn("Associate data not loaded");
//                throw new AssociateNotfoundException();
//            }
//        }else{
//            List<Associate> allAssociateList =  repo.findAll();
//            if(null == allAssociateList){
//                log.error("Associate not found with given name");
//                throw new AssociateNotfoundException();
//            }else{
//                log.info("Associate found with given name");
//            }
//
//            for(Associate associate : allAssociateList){
//                String associateName = associate.getName();
//                String[] nameInitialsArray = associateName.split(" ");
//
//
//                //get the initails of each name
//                for(String initials : nameInitialsArray){
//                    nameInitials.append(initials.charAt(0));
//                }
//                System.out.println("Initails - " + nameInitials.toString());
//                if(nameFromUI.equals(nameInitials.toString())){
//                    filteredAssociateList.add(associate);
//                }else{
//                    nameInitials.delete(0,nameInitials.length());
//                }
//            }
//        }
//        return filteredAssociateList;
//    }
//
//
//    public List<Associate> getAssociatesBySkill(final String topic){
//        System.out.println("\n\n\n getAssociatesBySkill - " + topic);
//        //List<Associate> associateList = repo.getAssociateBySkill(topic);
//        List<Associate> associateList =  repo.findAll();
//        if(null == associateList){
//            log.warn("Associate not found with given Skillset");
//            //throw new AssociateNotfoundForGivenSkillException();
//        }else{
//            log.info("Associate found with given Skillset");
//        }
//        List<Associate> filteredAssociateList = new ArrayList<Associate>();
//        for(Associate data : associateList){
//            for(Skills skill : data.getTechnical_skills()){
//                if(topic.equals(skill.getTopic())){
//                    int rating = Integer.parseInt(skill.getRating());
//                    if(rating > 10){
//                        filteredAssociateList.add(data);
//                    }
//                }
//            }
//        }
//        return filteredAssociateList;
//    }
//
//
//    /**
//     * <p>This method saves the Associate Object recieved from the Command part of CQRS Pattern and saves into the
//     * Mongo DB
//     * </p>
//     * @param kafkaMessage -  JSON Entity object send from Angular UI to Maintain to here
//     */
////    public void saveProfileFromCQRS(final KafkaMessage kafkaMessage){
////        Associate associate = performModelTransformation(kafkaMessage.getProfile());
////        if(kafkaMessage.getMongoOpsCode().equals("INSERT") || kafkaMessage.getMongoOpsCode().equals("UPDATE")){
////            try{
////                repo.save(associate);
////                log.info("AssociateRepository - SAVE from KAKFA to MongoDB");
////            }catch(Exception e){
////                log.error("Associate data could not be saved to MongoDB");
////                throw new MongoDBRepoSaveException();
////            }
////
////        }
////
////    }
//
//
//    /**
//     * <p>This method transforms the incoming JSON Entity object to the MongoDB Entity Object that would
//     * have to be persisted. . .
//     * </p>
//     * @param profile -  JSON Entity object send from Angular UI to Maintain to here
//     * @return associate - the Entity object to be peristed into MongoDB
//     */
//    private Associate performModelTransformation (final Profile profile){
//        Associate associate = new Associate();
//        associate.setAssociateid(profile.getAssociateid());
//        associate.setName(profile.getName());
//        associate.setMobile(profile.getMobile());
//        associate.setEmail(profile.getEmail());
//        associate.setTechnical_skills(profile.getTechskills());
//        associate.setNon_technical_skills(profile.getNontechskills());
//        return associate;
//
//    }

}
