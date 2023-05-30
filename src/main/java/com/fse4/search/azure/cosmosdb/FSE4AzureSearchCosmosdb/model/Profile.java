package com.fse4.search.azure.cosmosdb.FSE4AzureSearchCosmosdb.model;


import com.fse4.search.azure.cosmosdb.FSE4AzureSearchCosmosdb.entity.Skills;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Profile implements Serializable {

    private String associateid;

    private String name;

    private String mobile;

    private String email;

    private List<Skills> techskills;

    private List<Skills> nontechskills;

    public Profile(String associateid, String name, String mobile, String email, List<Skills> techskills, List<Skills> nontechskills) {
        this.associateid = associateid;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.techskills = techskills;
        this.nontechskills = nontechskills;
    }
}
