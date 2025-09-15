using System.Text.Json;
using CartService.Models;
using Microsoft.Extensions.Caching.Distributed;

namespace CartService.Daos
{
    public class CartDao
    {
        private readonly IDistributedCache _cache;

        public CartDao(IDistributedCache cache)
        {
            _cache = cache;
        }

        public async Task<List<CartItem>> GetCartAsync(string userId)
        {
            var data = await _cache.GetStringAsync($"cart:{userId}");
            
            if (string.IsNullOrEmpty(data))
                return new List<CartItem>();

            return JsonSerializer.Deserialize<List<CartItem>>(data) ?? new List<CartItem>();
        }

        public async Task SaveCartAsync(string userId, List<CartItem> items, TimeSpan? ttl = null)
        {
            var data = JsonSerializer.Serialize(items);
            var options = new DistributedCacheEntryOptions{AbsoluteExpirationRelativeToNow = ttl ?? TimeSpan.FromHours(24)};
            await _cache.SetStringAsync($"cart:{userId}", data, options);
        }

        public async Task DeleteCartAsync(string userId)
        {
            await _cache.RemoveAsync($"cart:{userId}");
        }

        public async Task RefreshTtlAsync(string userId, TimeSpan ttl)
        {
            var data = await _cache.GetStringAsync($"cart:{userId}");
            if (string.IsNullOrEmpty(data)) return;

            var options = new DistributedCacheEntryOptions
            {
                AbsoluteExpirationRelativeToNow = ttl
            };

            await _cache.SetStringAsync($"cart:{userId}", data, options);
        }
    }
}