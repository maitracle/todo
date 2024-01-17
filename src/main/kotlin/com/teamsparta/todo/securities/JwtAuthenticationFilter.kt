package com.teamsparta.todo.securities

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val jwtPlugin: JwtPlugin
) : OncePerRequestFilter() {

    companion object {
        private val BEARER_PATTERN = Regex("^Bearer (.+?)$")
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val jwt = request.getBearerToken()

        jwt?.let { token ->
            jwtPlugin.validateToken(token)
                .onSuccess { decoded ->
                    val userId = decoded.payload.subject.toLong()
                    val username = decoded.payload.get("username", String::class.java)

                    val userPrincipal = UserPrincipal(userId, username)
                    val details = WebAuthenticationDetailsSource().buildDetails(request)
                    val auth = JwtAuthenticationToken(userPrincipal, details)

                    SecurityContextHolder.getContext().authentication = auth
                }
        }

        filterChain.doFilter(request, response)
    }

    private fun HttpServletRequest.getBearerToken(): String? {
        val headerValue = this.getHeader(HttpHeaders.AUTHORIZATION) ?: return null
        return BEARER_PATTERN.find(headerValue)?.groupValues?.get(1)
    }
}
