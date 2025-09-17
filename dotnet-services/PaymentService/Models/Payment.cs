using PaymentService.Enums;

namespace PaymentService.Models
{
    public class Payment
    {
        public long Id { get; set; }
        public long UserId { get; set; }
        public PaymentStatus Status { get; set; }
        public double Amount { get; set; }
        public DateTime CreatedAt { get; set; } = DateTime.Now;
    }
}
