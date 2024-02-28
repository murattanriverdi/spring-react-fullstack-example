import { useState, useEffect, useCallback } from "react";
import { LoadUserList } from "../api";
import { Spinner } from "@/utils/components/Spinner";
import { UserListItem } from "./UserListItem";

export function UserList() {
  const [userList, setUserList] = useState({
    content: [],
    last: false,
    first: false,
    number: 0,
  });

  const [isProcessing, setIsProcessing] = useState(false);

  const getUserList = useCallback(async (page) => {
    setIsProcessing(true);
    try {
      const response = await LoadUserList(page);
      setUserList(response.data);
    } catch (error) {
      console.error(error);
    } finally {
      setIsProcessing(false);
    }
  }, []);

  useEffect(() => {
    getUserList();
  }, []);

  return (
    <>
      <div className="card">
        <div className="card-header text-center fs-4">User List</div>
        <ul className="list-group list-group-flush">
          {userList.content.map((user) => {
            return <UserListItem key = {user.id} user = {user}/>;
          })}
        </ul>
        <div className="card-footer text-center">
          {isProcessing && <Spinner />}
          {!isProcessing && (
            <span>
              {!userList.first && (
                <button
                  className="btn btn-outline-secondary btn-sm float-start"
                  onClick={() => getUserList(userList.number - 1)}
                >
                  Previous
                </button>
              )}

              {!userList.last && (
                <button
                  className="btn btn-outline-secondary btn-sm float-end"
                  onClick={() => getUserList(userList.number + 1)}
                >
                  Next
                </button>
              )}
            </span>
          )}
        </div>
      </div>
    </>
  );
}
