import http from "@/lib/http";

export function getUserDetail(id){
    return http.get(`/api/v1/users/get/${id}`)
}