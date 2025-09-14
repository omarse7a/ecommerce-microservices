using System;

namespace CartService.Models
{
    public class CartItem
    {
        public long Id { get; set; }
        public int quantity { get; set; }
        public DateTime expiryTime { get; set; } = DateTime.Now.AddHours(24);
        public long userId { get; set; }
        public long productId { get; set; }
    }
}
