import axios from '../custom-axios/axios'
/* istanbul ignore next */
const MenuService={

    addNewMealToMenu:(name,description,price,mealCategory,imageUrl,ingredientsForMeal)=>{
        return axios.post("/api/meals/add",{
            "name":name,
            "description":description,
            "price":price,
            "mealCategory":mealCategory,
            "imageUrl":imageUrl,
            "ingredientsForMeal":ingredientsForMeal
        })
    },
    getDetailsForMeal:(id)=>{
        return axios.get(`/api/meals/${id}`);
    },
    updateMealFromMenu:(id,name,description,price,mealCategory,imageUrl,ingredientsForMeal)=>{
        return axios.put(`/api/meals/edit/${id}`,{
            "id":id,
            "name":name,
            "description":description,
            "price":price,
            "mealCategory":mealCategory,
            "imageUrl":imageUrl,
            "ingredientsForMeal":ingredientsForMeal
        })
    },
    getMeals:()=>{
        return axios.get("/api/meals/all");
    },
    getMealsFromCategory:(mealCategoryId)=>{
        return axios.get(`/api/meals/mealCategory/${mealCategoryId}`)
    },
    deleteMeal:(id)=>{
        return axios.delete(`/api/meals/delete/${id}`);
    }

}

export default MenuService;
