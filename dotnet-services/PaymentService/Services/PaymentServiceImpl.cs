using PaymentService.Data;
using PaymentService.Dtos;
using PaymentService.Enums;
using PaymentService.Models;

namespace PaymentService.Services
{
    public class PaymentServiceImpl : IPaymentService
    {
        private readonly ApplicationDbContext _db;
        public PaymentServiceImpl(ApplicationDbContext db)
        {
            _db = db;
        }
        public async Task<PaymentResponse> CreatePaymentAsync(PaymentRequest request)
        {
            var payment = new Payment
            {
                UserId = request.UserId,
                OrderId = request.OrderId,
                Status = PaymentStatus.Paid,
                Amount = request.Amount
            };
            _db.Payments.Add(payment);
            await _db.SaveChangesAsync();
            return new PaymentResponse
            {
                Id = payment.Id,
                UserId = payment.UserId,
                OrderId = payment.OrderId,
                Status = payment.Status,
                Amount = payment.Amount,
                TimeStamp = payment.CreatedAt
            };
        }

        public async Task<PaymentResponse> GetPaymentStatusAsync(string paymentId)
        {
            if (!long.TryParse(paymentId, out var id))
            {
                throw new ArgumentException($"Invalid payment ID ({paymentId})");
            }

            var payment = await _db.Payments.FindAsync(id);

            if (payment == null)
            {
                throw new KeyNotFoundException($"Payment with ID {paymentId} not found");
            }
            return new PaymentResponse
            {
                Id = payment.Id,
                UserId = payment.UserId,
                OrderId = payment.OrderId,
                Status = payment.Status,
                Amount = payment.Amount,
                TimeStamp = payment.CreatedAt
            };
        }

    }
}