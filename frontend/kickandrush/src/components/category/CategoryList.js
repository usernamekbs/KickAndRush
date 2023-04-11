import React, { useState,useEffect } from "react";
import AddCategory from "../category/AddCategory";
import Category from "./Category";

const CategoryList = ({props,delCategory,addCategory2,updCategory}) => {
    const [category,setCategory] = useState(props);
    
    useEffect(() => {  
        setCategory(props)
    }); 

    const addCategory = (newCategory) =>{
        setCategory(category.concat(newCategory))
        addCategory2(newCategory)
    }
    
    return ( 

        <div>
            {
              category.map((c)=>{
                return <div key={c.ctg_cno} style={{marginLeft:'30'}}>
                            <Category props={c} setCategory={setCategory} delCategory={delCategory} updCategory={updCategory}></Category>
                        </div>
              }) 
              
            }
            <AddCategory refreshFunction={addCategory}/>
        </div>
        
    ); 
};
  
  
export default CategoryList;