import PropTypes from "prop-types";
import defaultProfileImage from "@/assets/profile.png";
import { Link } from "react-router-dom";

export function UserListItem({ user }) {
  return (
    <Link
      to={`/user/${user.id}`}
      className="list-group-item list-group-item-action"
    >
      <img
        src={defaultProfileImage}
        width="30"
        className="img-fluid rounded-circle shadow-sm"
      />
      <span className="ms-2">{user.username}</span>
    </Link>
  );
}

UserListItem.propTypes = {
  user: PropTypes.object,
};
