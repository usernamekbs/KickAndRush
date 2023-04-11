import http from "../http-common";

const getAll = () => {
  return http.get("/category/list"); 
}; 
 
const addCategory = (data) => {
    return http.post("/category/create",data); 
  };

const deleteCategory = ctg_cno => {
    return http.delete("/category/delete/"+ctg_cno);
};

const updateCategory = (data) => {
    return http.patch("/category/update",data);
  }

export default {
  getAll,
  addCategory,
  deleteCategory,
  updateCategory
}; 