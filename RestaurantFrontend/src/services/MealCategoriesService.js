import axios from "../custom-axios/axios";
/* istanbul ignore next */
const MealCategoriesService={

    getAllMealCategories:()=>{
        return axios.get("/api/mealCategories");
    },
    addNewMealCategory:(name,imageUrl)=>{
        return axios.post("/api/mealCategories/add",{
            "name":name,
            "imageUrl":imageUrl,
            "numberOfMeals":0
        })
    },
    deleteMealCategory:(id)=>{
        return axios.delete(`/api/mealCategories/delete/${id}`)
    },
    editMealCategory:(id,name,imageUrl)=>{
        return axios.put(`/api/mealCategories/edit/${id}`,{
            "id":id,
            "name":name,
            "imageUrl":imageUrl,
            "numberOfMeals":0
        })
    },
    getMealCategoryDetails:(id)=>{
        return axios.get(`/api/mealCategories/${id}`)
    }
}

export default MealCategoriesService;