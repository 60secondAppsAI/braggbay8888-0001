<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <paymentTransaction-table
            v-if="paymentTransactions && paymentTransactions.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:paymentTransactions="paymentTransactions"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-payment-transactions="getAllPaymentTransactions"
             >

            </paymentTransaction-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import PaymentTransactionTable from "@/components/PaymentTransactionTable";
import PaymentTransactionService from "../services/PaymentTransactionService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    PaymentTransactionTable,
  },
  data() {
    return {
      paymentTransactions: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllPaymentTransactions(sortBy='paymentTransactionId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await PaymentTransactionService.getAllPaymentTransactions(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.paymentTransactions.length) {
					this.paymentTransactions = response.data.paymentTransactions;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching paymentTransactions:", error);
        }
        
      } catch (error) {
        console.error("Error fetching paymentTransaction details:", error);
      }
    },
  },
  mounted() {
    this.getAllPaymentTransactions();
  },
  created() {
    this.$root.$on('searchQueryForPaymentTransactionsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllPaymentTransactions();
    })
  }
};
</script>
<style></style>
