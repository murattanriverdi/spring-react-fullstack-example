import React, { Component } from 'react'
import axios from 'axios'


function getDisplayName(WrappedComponent) {
    return WrappedComponent.getDisplayName || WrappedComponent.name || "Component";
    
}

//high order component function
export function withApiProgress(WrappedComponent, apiPath) {
    return class extends Component {

        static displayName = `ApiProgress(${getDisplayName(WrappedComponent)})`;

        state = {
            isApiCallWaiting: false
        }

        // component ilk oluştuğunda çalışan life cycle
        componentDidMount() {
            axios.interceptors.request.use((request) => {
                this.updateApiCallWaiting(request.url, true)
                return request;
            })

            axios.interceptors.response.use((response) => {
                this.updateApiCallWaiting(response.config.url, false)
                return response;
            }, (error) => {
                this.updateApiCallWaiting(error.config.url, false)
                throw error;
            })
        }

        updateApiCallWaiting = (url, status) => {
            if (url === apiPath) {
                this.setState({ isApiCallWaiting: status })
            }
        }

        render() {
            const { isApiCallWaiting } = this.state
            return <WrappedComponent isApiCallWaiting={isApiCallWaiting} {... this.props} />
        }
    }
}



