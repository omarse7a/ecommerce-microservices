using System.ComponentModel.DataAnnotations;

namespace PaymentService.Dtos
{
    public class PaymentRequest
    {
        [Required]
        public long UserId { get; set; }
        [Required]
        public long OrderId { get; set; }
        [Required]
        public double Amount { get; set; }
    }
}