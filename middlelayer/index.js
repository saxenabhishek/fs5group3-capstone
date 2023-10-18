const express = require('express');
const axios = require('axios');
const app = express();

const port = 9090;
app.use(express.json());

REROUTE_BASE_URL = "http://localhost:8080"

const routeRules = [
  {
    method: 'POST',
    route: '/client/register',
    redirect: '/client/register'
  },
  {
    method: 'POST',
    route: '/client/login',
    redirect: '/client/login'
  },
  {
    method: 'GET',
    route: '/client/verify-email/:email',
    redirect: (req) => `/client/verify-email/${req.params.email}`
  },
  {
    method: 'GET',
    route: '/client/preference/:id',
    redirect: (req) => `/client/preference/${req.params.id}`
  },
  {
    method: 'POST',
    route: '/client/preference/add',
    redirect: '/client/preference/add'
  },
  {
    method: 'PUT',
    route: '/client/preference/update',
    redirect: '/client/preference/update'
  },
  {
    method: 'GET',
    route: '/client/activity-report/:clientId/:direction',
    redirect: (req) => `/client/activity-report/${req.params.clientId}/${req.params.direction}`
  
  },
  //Trades
  {
    method: 'GET',
    route: '/trade',
    redirect: '/trade'
  },
  {
    method: 'GET',
    route: '/trade/robo-advisor/:clientID',
    redirect: (req) => `/trade/robo-advisor/${req.params.clientID}`
  },
  {
    method: 'POST',
    route: '/trade/make-trade',
    redirect: '/trade/make-trade'
  },
  {
    method: 'GET',
    route: '/trade/instruments',
    redirect: '/trade/instruments'
  }
];
const createAxiosConfig = (method, url, query, data) => {
  const axiosConfig = {
    method,
    url,
    params: query
  };

  if (method !== 'GET') {
    axiosConfig.data = data;
  }

  return axiosConfig;
};

const handleRequest = async (req, res, method, redirectURL) => {
  try {
    let redirectPath;

    if (typeof redirectURL === 'function') {
      redirectPath = redirectURL(req);
    } else {
      redirectPath = redirectURL;
    }

    const axiosConfig = createAxiosConfig(method, REROUTE_BASE_URL + redirectPath, req.query, req.body);
    const response = await axios(axiosConfig);
    res.status(response.status).send(response.data);
  } catch (error) {
    console.error('Error:', error);
    res.status(500).send('Error occurred while fetching data');
  }
};

routeRules.forEach(rule => {
  const { method, route, redirect } = rule;

  switch (method) {
    case 'GET':
      app.get(route, (req, res) => {
        handleRequest(req, res, method, redirect);
      });
      break;

    case 'POST':
      app.post(route, (req, res) => {
        handleRequest(req, res, method, redirect);
      });
      break;
  }
});

app.listen(port, () => {
  console.log('Server is running on port ' + port);
});