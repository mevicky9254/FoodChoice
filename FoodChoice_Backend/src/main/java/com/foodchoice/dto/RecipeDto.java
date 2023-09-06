package com.foodchoice.dto;

import java.util.ArrayList;
import java.util.List;



import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor

public class RecipeDto {
	
   
    private String title;
    private String type;
    
    private String image;
    private String description;
    private List<String> instructions=new ArrayList<>();
    private List<String> ingredients=new ArrayList<>();
    
    
    
    
}
