import http from "../http-common"; 

class PaymentTransactionService {
  getAllPaymentTransactions(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/paymentTransaction/paymentTransactions`, searchDTO);
  }

  get(paymentTransactionId) {
    return this.getRequest(`/paymentTransaction/${paymentTransactionId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/paymentTransaction?field=${matchData}`, null);
  }

  addPaymentTransaction(data) {
    return http.post("/paymentTransaction/addPaymentTransaction", data);
  }

  update(data) {
  	return http.post("/paymentTransaction/updatePaymentTransaction", data);
  }
  
  uploadImage(data,paymentTransactionId) {
  	return http.postForm("/paymentTransaction/uploadImage/"+paymentTransactionId, data);
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

export default new PaymentTransactionService();
