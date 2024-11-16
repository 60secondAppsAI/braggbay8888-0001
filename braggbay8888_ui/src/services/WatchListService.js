import http from "../http-common"; 

class WatchListService {
  getAllWatchLists(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/watchList/watchLists`, searchDTO);
  }

  get(watchListId) {
    return this.getRequest(`/watchList/${watchListId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/watchList?field=${matchData}`, null);
  }

  addWatchList(data) {
    return http.post("/watchList/addWatchList", data);
  }

  update(data) {
  	return http.post("/watchList/updateWatchList", data);
  }
  
  uploadImage(data,watchListId) {
  	return http.postForm("/watchList/uploadImage/"+watchListId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new WatchListService();
