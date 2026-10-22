package filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * EncodingFilter - Bộ lọc mã hóa UTF-8 toàn bộ website.
 * Đảm bảo các ký tự tiếng Việt từ Form và cơ sở dữ liệu hiển thị chính xác.
 */
@WebFilter("/*")
public class EncodingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Khởi tạo nếu cần
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // Thiết lập bộ mã hóa UTF-8 cho request và response
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        // Cho phép request đi tiếp đến servlet hoặc filter tiếp theo
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Hủy bộ lọc nếu cần
    }
}
