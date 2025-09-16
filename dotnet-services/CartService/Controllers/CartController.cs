using CartService.Daos;
using CartService.Models;
using Microsoft.AspNetCore.Mvc;

namespace CartService.Controllers
{
    [ApiController]
    [Route("api/v1/carts")]
    public class CartController : ControllerBase
    {
        private readonly CartDao _cartDao;

        public CartController(CartDao cartDao)
        {
            _cartDao = cartDao;
        }

        [HttpGet("{userId}")]
        public async Task<IActionResult> GetCart(string userId)
        {
            var cart = await _cartDao.GetCartAsync(userId);
            return Ok(cart);
        }

        [HttpPost("{userId}")]
        public async Task<IActionResult> SetCart(string userId, [FromBody] List<CartItem> items)
        {
            await _cartDao.SaveCartAsync(userId, items);
            return Ok();
        }

        [HttpPut("{userId}")]
        public async Task<IActionResult> RefreshCartTtl(string userId, [FromQuery(Name = "ttl")] int ttlInDays)
        {
            var ttl = TimeSpan.FromDays(ttlInDays);
            await _cartDao.RefreshTtlAsync(userId, ttl);
            return Ok($"TTL was updated to {ttl.TotalSeconds} seconds");
        }

        [HttpDelete("{userId}")]
        public async Task<IActionResult> ClearCart(string userId)
        {
            await _cartDao.DeleteCartAsync(userId);
            return NoContent();
        } 
    }
}