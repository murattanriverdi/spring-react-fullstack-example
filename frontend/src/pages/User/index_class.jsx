import { Component } from "react";
import { useParams } from "react-router-dom";
import { getUserDetail } from "./api";
import PropTypes from "prop-types";
import { Alert } from "@/utils/components/Alert";
import { Spinner } from "@/utils/components/Spinner";
import { withTranslation } from "react-i18next";

export class UserClass extends Component {
  state = {
    user: null,
    isProcessing: false,
    error: null,
  };


  loadUser = async () => {
    this.setState({ isProcessing: true });
    try {
      const response = await getUserDetail(this.props.id);
      this.setState({
        user: response.data,
      });
    } catch (error) {
      this.setState({ error: this.props.t("userNotFound") });
    } finally {
      this.setState({ isProcessing: false });
    }
  }

  //component ilk yüklendiğinde
  async componentDidMount() {
   this.loadUser();
  }

  //component güncellendiğinde
  componentDidUpdate(previousProps){
    if(this.props.id !== previousProps.id){
      this.loadUser();
    }
  }

  //component ekrandan çıkarılınca.
  componentWillUnmount(){

  }

  render() {
    const { user, isProcessing, error } = this.state;

    return (
      <>
        {user && <h1>{user.username}</h1>}
        {isProcessing && (
          <Alert styleType="secondary" center>
            <Spinner />
          </Alert>
        )}
        {error && <Alert styleType="danger">{error}</Alert>}
      </>
    );
  }
}

UserClass.propTypes = {
  id: PropTypes.string,
  t: PropTypes.func,
};

//higher order component
const UserClassWithTranslation = withTranslation()(UserClass);

export function UserFunction() {
  const { id } = useParams();
  return <UserClassWithTranslation id={id} />;
}
