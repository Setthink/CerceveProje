import axios from "axios";

const API_URL = "http://localhost:8080/";

class ApiService {
  getEntityData(entityType) {
    return axios.get(API_URL + entityType.toLowerCase() + "/all");
  }

  postEntityData(entityType, data) {
    return axios.post(API_URL + entityType.toLowerCase(), data);
  }

  putEntityData(entityType, data) {
    return axios.put(API_URL + entityType.toLowerCase(), data);
  }

  deleteEntityData(entityType, id) {
    return axios.delete(API_URL + entityType.toLowerCase() + "/" + id);
  }

  getSiparisByMusteriId(musteriId) {
    return axios.get(API_URL + "siparis/musteri/" + musteriId);
  }
}

export default new ApiService();
