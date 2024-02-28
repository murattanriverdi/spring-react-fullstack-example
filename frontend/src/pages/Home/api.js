import http from "@/lib/http";

export function LoadUserList(page = 0){
    return http.get("/api/v1/users/list", { 
        params : {
            page: page,
            size: 5
        }
     })
}