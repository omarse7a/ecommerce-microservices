namespace PaymentService.Client
{
    public class CartClient
    {
        private readonly HttpClient _httpClient;
        public CartClient(HttpClient httpClient)
        {
            _httpClient = httpClient;
        }

        public async Task<bool> ClearCartAsync(string userId)
        {
            var response = await _httpClient.DeleteAsync($"/api/cart/{userId}");
            return response.IsSuccessStatusCode;
        }
    }
}