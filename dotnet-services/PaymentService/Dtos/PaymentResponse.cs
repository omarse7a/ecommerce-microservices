using PaymentService.Enums;

namespace PaymentService.Dtos
{
    public class PaymentResponse
    {
        public long Id { get; set; }
        public long UserId { get; set; }
        public long OrderId { get; set; }
        public PaymentStatus Status { get; set; }
        public double Amount { get; set; }
        public DateTime TimeStamp { get; set; }
    }
}