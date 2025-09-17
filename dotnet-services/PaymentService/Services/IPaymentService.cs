using PaymentService.Dtos;

namespace PaymentService.Services
{
    public interface IPaymentService
    {
        Task<PaymentResponse> CreatePaymentAsync(PaymentRequest request);
        Task<PaymentResponse> GetPaymentStatusAsync(string paymentId);
    }
}
